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
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class EditAddActivity extends AppCompatActivity implements
        InOutFragment.OnInOutInteractionListener,
        DetailedFragment.OnDetailedInteractionListener,
        ViewPager.OnPageChangeListener
{
    // mode (add or edit)
    public static final int MODE_NONE = 0;
    public static final int MODE_ADD = 1;
    public static final int MODE_EDIT = 2;
    private int mode = MODE_NONE;

    // mode edit: fragments to be shown by the pager
    private static final int EDIT_PAGE_IN_OUT = 0;
    private static final int EDIT_PAGE_DETAILED = 1;
    private static final int EDIT_PAGE_PHOTO = 2;
    private static final int EDIT_PAGES_NB = 2;
    private static final int EDIT_DEFAULT_PAGE = EDIT_PAGE_IN_OUT;

    // mode add: fragments to be shown by the pager
    private static final int ADD_PAGE_DETAILED = 0;
    private static final int ADD_PAGE_PHOTO = 1;
    private static final int ADD_PAGES_NB = 1;
    private static final int ADD_DEFAULT_PAGE = ADD_PAGE_DETAILED;

    // fragments in a pager
    CollectionPagerAdapter collectionPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editadd);

        // activity mode
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            mode = bundle.getInt("mode");
            if (mode != MODE_ADD && mode != MODE_EDIT)
            {
                // TODO: afficher quelque chose
                finish();
            }
        }

        // pager
        collectionPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(collectionPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        // statusbar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        // toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.activity_edit_title);

        // setup MMI according to mode
        switch(mode)
        {
            case MODE_ADD:
                // Default view at startup
                viewPager.setCurrentItem(ADD_DEFAULT_PAGE);
                getSupportActionBar().setTitle(R.string.activity_add_title);
                break;
            case MODE_EDIT:
                // Default view at startup
                viewPager.setCurrentItem(EDIT_DEFAULT_PAGE);
                getSupportActionBar().setTitle(R.string.activity_edit_title);
                break;
            default:
                break;
        }
     }

    private void setReadOnly(View view, final int resource)
    {
        try
        {
            if (view != null)
            {
                ((EditText)view.findViewById(resource)).setEnabled(false);
            }
        }
        finally
        {
            // nothing
        }
    }

    public void onClickActionInOut(View view)
    {
    }

    private void checkShouldntBeVoid(final View view, final int resource)
    {
        // set error if fields does not match entry rules
        EditText edit = (EditText)view.findViewById(resource);
        if (edit != null)
        {
            edit.addTextChangedListener(new TextWatcher()
            {
                // field validation for non-void fields
                public void afterTextChanged(Editable s)
                {
                    final EditText edit = (EditText)findViewById(resource);
                    if (s.length() == 0 && edit != null)
                    {
                        edit.setError(getResources().getString(R.string.mandatory_field));
                        edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error_24dp, 0);
                    }
                    else
                    {
                        edit.setError(null);
                        edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                }

                // not used (however must be present)
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {
                }

                // not used (however must be present)
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                }
            });
        }
    }

    // interactions with fragments
    public void onInOutCreateView(View view)
    {
        // fill MMI views from db fields
        DbPiece piece = MainActivity.getLastRequestedPiece();
        ((EditText)view.findViewById(R.id.editQRCode)).setText(piece.get("qrcode"));
        ((EditText)view.findViewById(R.id.editReference)).setText(piece.get("reference"));
        ((EditText)view.findViewById(R.id.editLongueur)).setText(piece.get("longueur"));

        // read-only fields
        setReadOnly(view, R.id.editQRCode);
        setReadOnly(view, R.id.editReference);

        // check fields
        checkShouldntBeVoid(view, R.id.editLongueur);
        checkShouldntBeVoid(view, R.id.editLargeur);
        checkShouldntBeVoid(view, R.id.editGrammage);

        // 'out' button state
        computeInOutButton(view);

        ((EditText)view.findViewById(R.id.editQRCode)).getParent();
    }

    public void computeInOutButton(View view)
    {
        ((Button)view.findViewById(R.id.buttonActionInOut)).setEnabled(false);
    }

    public void onDetailedCreateView(View view)
    {
        // fill MMI views from db fields
        int[] edits = new int [] {
                R.id.editQRCode, R.id.editFournisseur, R.id.editRefFournisseur,
                R.id.editDateArrivee, R.id.editTransportFrigo, R.id.editLongueurInitiale,
                R.id.editLargeur, R.id.editGrammage, R.id.editTypeDeTissus
        };
        String[] dbfields = new String [] {
                "qrcode", "fournisseur", "reffournisseur",
                "datedarrivee", "transportfrigo", "longueurinitiale",
                "largeur", "grammage", "typedetissus"
        };
        DbPiece piece = MainActivity.getLastRequestedPiece();
        int text_id = 0;
        for (int id : edits)
        {
            try {
                String value = piece.get(dbfields[text_id]);
                ((EditText) view.findViewById(id)).setText(value);
            }
            catch(Exception e) {
                Toast.makeText(this, "Error with field \"" + dbfields[text_id] + "\"", Toast.LENGTH_SHORT).show();
            }
            text_id += 1;
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
        if (mode == MODE_EDIT)
        {
            switch (position)
            {
                case EDIT_PAGE_IN_OUT:
                    getSupportActionBar().setTitle(R.string.activity_edit_title);
                    break;
                case EDIT_PAGE_DETAILED:
                    getSupportActionBar().setTitle(R.string.activity_edit_detailed_title);
                    break;
            }
        }
        else if (mode == MODE_ADD)
        {
            switch (position)
            {
                case ADD_PAGE_DETAILED:
                    getSupportActionBar().setTitle(R.string.activity_add_title);
                    break;
                case ADD_PAGE_PHOTO:
                    getSupportActionBar().setTitle(R.string.activity_edit_detailed_title);
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            int currentPage = viewPager.getCurrentItem();

            switch(currentPage)
            {
                case EDIT_PAGE_IN_OUT:
                    onBackPressed();
                    break;
                default:
                    //if (currentPage > 0)
                    //    viewPager.setCurrentItem(currentPage - 1);
                    break;
            }
            return super.onOptionsItemSelected(item);
        }

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
            if (parentActivity.mode == MODE_EDIT)
            {
                switch (i)
                {
                    // main fragment
                    case EditAddActivity.EDIT_PAGE_IN_OUT:
                        return new InOutFragment();
                    // other
                    case EditAddActivity.EDIT_PAGE_DETAILED:
                        return new DetailedFragment();
                    // other
                    case EditAddActivity.EDIT_PAGE_PHOTO:
                        return new DetailedFragment();
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
                    // other
                    case EditAddActivity.ADD_PAGE_PHOTO:
                        return new DetailedFragment();
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public int getCount()
        {
            if (parentActivity.mode == MODE_EDIT)
                return parentActivity.EDIT_PAGES_NB;
            else
                return parentActivity.ADD_PAGES_NB;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            Log.i("getPageTitle", "" + position);
            return "page " + position;
        }
    }
}
