package com.lrubstudio.workshape;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditAddActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener
{
    // mode (add or edit)
    public static final int MODE_NONE = 0;
    public static final int MODE_ADD = 1;
    public static final int MODE_EDIT_OUT = 2;
    public static final int MODE_EDIT_IN = 3;
    private int mode = MODE_NONE;

    // mode edit (piece to be put out or in): fragments to be shown by the pager
    private static final int EDIT_PAGE_IN_OUT = 0;
    private static final int EDIT_PAGE_DETAILED = 1;
    private static final int EDIT_PAGE_NOTE = 2;
    private static final int EDIT_PAGES_NB = 3;
    private static final int EDIT_DEFAULT_PAGE = EDIT_PAGE_IN_OUT;

    // mode add: fragments to be shown by the pager
    private static final int ADD_PAGE_DETAILED = 0;
    private static final int ADD_PAGE_NOTE = 1;
    private static final int ADD_PAGES_NB = 2;
    private static final int ADD_DEFAULT_PAGE = ADD_PAGE_DETAILED;

    // fragments in a pager
    CollectionPagerAdapter collectionPagerAdapter;
    ViewPager viewPager;

    // used by fragments: indicates if a fragment was modified
    boolean isModified = false;
    public void setModified(boolean modified)
    {
        isModified = modified;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editadd);

        // determine mode: add, edit for out, edit for in
        if (MainActivity.getLastRequestedPiece().isNewQrCode())
            mode = MODE_ADD;
        else if (MainActivity.getLastRequestedPiece().isProductOut())
            mode = MODE_EDIT_IN;
        else
            mode = MODE_EDIT_OUT;

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
            case MODE_ADD:
                viewPager.setCurrentItem(ADD_DEFAULT_PAGE);
                getSupportActionBar().setTitle(R.string.activity_add_title);
                break;
            case MODE_EDIT_OUT:
                viewPager.setCurrentItem(EDIT_DEFAULT_PAGE);
                getSupportActionBar().setTitle(R.string.activity_piece_out);
                break;
            case MODE_EDIT_IN:
                viewPager.setCurrentItem(EDIT_DEFAULT_PAGE);
                getSupportActionBar().setTitle(R.string.activity_piece_in);
                break;
            default:
                break;
        }
     }

    /*
     * For interface ViewPager.OnPageChangeListener
     */
    public void onPageScrollStateChanged (int state)
    {
        // nothing
    }

    public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels)
    {
        // nothing
    }

    public void onPageSelected (int position)
    {
        if (mode == MODE_EDIT_OUT)
        {
            switch (position)
            {
                case EDIT_PAGE_IN_OUT:
                    getSupportActionBar().setTitle(R.string.activity_piece_out);
                    break;
                case EDIT_PAGE_DETAILED:
                    getSupportActionBar().setTitle(R.string.activity_edit_detailed_title);
                    break;
                case EDIT_PAGE_NOTE:
                    getSupportActionBar().setTitle(R.string.activity_edit_note_title);
                    break;
            }
        }
        else if (mode == MODE_EDIT_IN)
        {
            switch (position)
            {
                case EDIT_PAGE_IN_OUT:
                    getSupportActionBar().setTitle(R.string.activity_piece_in);
                    break;
                case EDIT_PAGE_DETAILED:
                    getSupportActionBar().setTitle(R.string.activity_edit_detailed_title);
                    break;
                case EDIT_PAGE_NOTE:
                    getSupportActionBar().setTitle(R.string.activity_edit_note_title);
            }
        }
        else if (mode == MODE_ADD)
        {
            switch (position)
            {
                case ADD_PAGE_DETAILED:
                    getSupportActionBar().setTitle(R.string.activity_add_title);
                    break;
                case ADD_PAGE_NOTE:
                    getSupportActionBar().setTitle(R.string.activity_edit_note_title);
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        if (isModified)
        {
            // check if modifications
            if ((new SyncDialog()).run(this, getResources().getString(R.string.modifs_non_sauvees), getResources().getString(R.string.question_confirmer), getResources().getString(R.string.reponse_abandonner), getResources().getString(R.string.reponse_rester)))
                finish();
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
        public Fragment getItem(int i)
        {
            // mode edit
            if (parentActivity.mode == MODE_EDIT_OUT)
            {
                switch (i)
                {
                    // main fragment
                    case EditAddActivity.EDIT_PAGE_IN_OUT:
                        return new OutFragment();
                    // detailed
                    case EditAddActivity.EDIT_PAGE_DETAILED:
                        return new DetailedFragment();
                    // note
                    case EditAddActivity.EDIT_PAGE_NOTE:
                        return new NoteFragment();
                    default:
                        break;
                }
            }
            else if (parentActivity.mode == MODE_EDIT_IN)
            {
                switch (i)
                {
                    // main fragment
                    case EditAddActivity.EDIT_PAGE_IN_OUT:
                        return new InFragment();
                    // detailed
                    case EditAddActivity.EDIT_PAGE_DETAILED:
                        return new DetailedFragment();
                    // note
                    case EditAddActivity.EDIT_PAGE_NOTE:
                        return new NoteFragment();
                    default:
                        break;
                }
            }
            else if (parentActivity.mode == MODE_ADD)
            {
                switch (i)
                {
                    // main fragment
                    case EditAddActivity.ADD_PAGE_DETAILED:
                        return new DetailedFragment();
                    // note
                    case EditAddActivity.ADD_PAGE_NOTE:
                        return new NoteFragment();
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public int getCount()
        {
            if (parentActivity.mode == MODE_ADD)
                return parentActivity.ADD_PAGES_NB;
            else
                return parentActivity.EDIT_PAGES_NB;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            Log.i("getPageTitle", "" + position);
            return "page " + position;
        }
    }
}
