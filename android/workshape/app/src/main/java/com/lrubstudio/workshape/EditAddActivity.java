package com.lrubstudio.workshape;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;

public class EditAddActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener, SyncDialog.NoticeSyncDialogListener
{
    // mode (add or edit)
    public enum Mode
    {
        none,
        add,
        edit_out,
        edit_in
    }
    private Mode mode = Mode.none;

    // mode edit (product to be put out or in): fragments to be shown by the pager
    private enum EditPage
    {
        in_out,
        detailed,
        histo,
        note
    }
    private static final EditPage editDefaultPage = EditPage.in_out;

    // mode add: fragments to be shown by the pager
    private enum AddPage
    {
        detailed
    }
    private static final AddPage addDefaultPage = AddPage.detailed;

    // fragments in a pager
    CollectionPagerAdapter collectionPagerAdapter;
    ViewPager viewPager;

    // used by fragments: indicates if a fragment was modified
    boolean isDetailedFragmentModified = false;
    public void setDetailedFragmentModified(boolean modified)
    {
        isDetailedFragmentModified = modified;
    }

    public boolean isDetailedFragmentModified()
    {
        return isDetailedFragmentModified;
    }

    boolean isNoteFragmentModified = false;
    public void setNoteFragmentModified(boolean modified)
    {
        isNoteFragmentModified = modified;
    }
    public boolean isNoteFragmentModified()
    {
        return isNoteFragmentModified;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editadd);

        // determine mode: add, edit for out, edit for in
        if (MainActivity.getLastRequestedProduct().isNewQrCode())
            mode = Mode.add;
        else if (MainActivity.getLastRequestedProduct().isProductOut())
            mode = Mode.edit_in;
        else
            mode = Mode.edit_out;

        // pager
        collectionPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(collectionPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        // toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.activity_edit_title);

        // set pager title
        switch(mode)
        {
            case add:
                viewPager.setCurrentItem(addDefaultPage.ordinal());
                getSupportActionBar().setTitle(R.string.activity_add_title);
                break;
            case edit_out:
                viewPager.setCurrentItem(editDefaultPage.ordinal());
                getSupportActionBar().setTitle(R.string.activity_product_out);
                break;
            case edit_in:
                viewPager.setCurrentItem(editDefaultPage.ordinal());
                getSupportActionBar().setTitle(R.string.activity_product_in);
                break;
            default:
                break;
        }
     }

    /*
     * For interface ViewPager.OnPageChangeListener
     */
    @Override
    public void onPageScrollStateChanged (int state)
    {
        // hide keyboard if shown
        if (state == ViewPager.SCROLL_STATE_IDLE)
        {
            if (mode == Mode.edit_out || mode == Mode.edit_in)
            {
                // Hide the keyboard.
                ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
            }
        }
    }

    public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels)
    {
        // nothing
    }

    public void onPageSelected (int position)
    {
        EditPage page = EditPage.values()[position];
        switch(mode)
        {
            case edit_out:
                switch (page)
                {
                    case in_out:
                        getSupportActionBar().setTitle(R.string.activity_product_out);
                        break;
                    case detailed:
                        getSupportActionBar().setTitle(R.string.activity_edit_detailed_title);
                        break;
                    case note:
                        getSupportActionBar().setTitle(R.string.activity_edit_note_title);
                        break;
                    case histo:
                        getSupportActionBar().setTitle(R.string.activity_edit_histo_title);
                        break;
                }
                break;
            case edit_in:
                switch (page)
                {
                    case in_out:
                        getSupportActionBar().setTitle(R.string.activity_product_in);
                        break;
                    case detailed:
                        getSupportActionBar().setTitle(R.string.activity_edit_detailed_title);
                        break;
                    case note:
                        getSupportActionBar().setTitle(R.string.activity_edit_note_title);
                        break;
                    case histo:
                        getSupportActionBar().setTitle(R.string.activity_edit_histo_title);
                        break;
                }
                break;
            case add:
                switch (page)
                {
                    case detailed:
                        getSupportActionBar().setTitle(R.string.activity_add_title);
                        break;
                }
                break;
        }
    }

    public void onSyncDialogChoice(boolean positive)
    {
        // user replied positively : cancel modifications by finishing the activity
        if (positive)
        {
            finish();
        }
    }

    @Override
    public void onBackPressed()
    {
        if (isDetailedFragmentModified || isNoteFragmentModified)
        {
            // ask user if really wants to quit if a fragment is modified
            (new SyncDialog(this)).run(this, getResources().getString(R.string.modifs_non_sauvees), getResources().getString(R.string.question_confirmer), getResources().getString(R.string.reponse_abandonner), getResources().getString(R.string.reponse_rester));
        }
        else
            finish();
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        // back to previous page
        int currentPage = viewPager.getCurrentItem();
        if (currentPage > 0)
            viewPager.setCurrentItem(currentPage - 1);
        else
            // from first page do the same as home button
            onBackPressed();
        return true;
    }

    //
    public static class CollectionPagerAdapter extends FragmentPagerAdapter
    {
        EditAddActivity parentActivity =  null;

        //
        public CollectionPagerAdapter(FragmentManager fm, EditAddActivity parentActivity)
        {
            super(fm);
            this.parentActivity = parentActivity;
        }

        @Override
        public Fragment getItem(int item)
        {
            switch(parentActivity.mode)
            {
                case edit_out:
                {
                    EditPage editPage = EditPage.values()[item];
                    switch (editPage)
                    {
                        // main fragment
                        case in_out:
                            return new OutFragment();
                        // detailed
                        case detailed:
                            return new DetailedFragment();
                        // note
                        case note:
                            return new NoteFragment();
                        // histo
                        case histo:
                            return new HistoFragment();
                        default:
                            break;
                    }
                }
                break;
                case edit_in:
                {
                    EditPage editPage = EditPage.values()[item];
                    switch (editPage)
                    {
                        // main fragment
                        case in_out:
                            return new InFragment();
                        // detailed
                        case detailed:
                            return new DetailedFragment();
                        // note
                        case note:
                            return new NoteFragment();
                        // histo
                        case histo:
                            return new HistoFragment();
                        default:
                            break;
                    }
                }
                break;
                case add:
                {
                    AddPage addPage = AddPage.values()[item];
                    switch (addPage)
                    {
                        // main fragment
                        case detailed:
                            return new DetailedFragment();
                        default:
                            break;
                    }
                }
                break;
            }
            return null;
        }

        @Override
        public int getCount()
        {
            int count = 0;
            switch(parentActivity.mode)
            {
                case add:
                    count = AddPage.values().length;
                    break;
                case edit_in:
                case edit_out:
                    count = EditPage.values().length;
                    break;
            }
            return count;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return "page " + position;
        }
    }
}
