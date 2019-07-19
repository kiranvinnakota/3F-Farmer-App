package com.calibrage.a3ffarmerapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.calibrage.a3ffarmerapp.Activities.CollectionCenterActivity;
import com.calibrage.a3ffarmerapp.Activities.CollectionsActivity;
import com.calibrage.a3ffarmerapp.Activities.EncyclopediaActivity;
import com.calibrage.a3ffarmerapp.Activities.FertilizerActivity;
import com.calibrage.a3ffarmerapp.Activities.FertilizerCenterActivity;
import com.calibrage.a3ffarmerapp.Activities.LabourActivity;
import com.calibrage.a3ffarmerapp.Activities.LabourRecommendationsActivity;
import com.calibrage.a3ffarmerapp.Activities.LoanActivity;
import com.calibrage.a3ffarmerapp.Activities.OtpActivity;
import com.calibrage.a3ffarmerapp.Activities.PaymentActivity;
import com.calibrage.a3ffarmerapp.Activities.PoleActivity;
import com.calibrage.a3ffarmerapp.Activities.QuickPayActivity;
import com.calibrage.a3ffarmerapp.Activities.RecommendationActivity;
import com.calibrage.a3ffarmerapp.Activities.RequestVisitActivity;
import com.calibrage.a3ffarmerapp.Activities.VisitActivity;
import com.calibrage.a3ffarmerapp.Adapters.KnowledgeZoneBaseAdapter;
import com.calibrage.a3ffarmerapp.Adapters.KnowledgeZoneCategoryAdapter;
import com.calibrage.a3ffarmerapp.Adapters.LabourRecommendationAdapter;
import com.calibrage.a3ffarmerapp.Model.Album;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    ImageButton dail;
    RecyclerView recyclerView;
    private List<Album> albumList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
       // setHasOptionsMenu(true);

        view.findViewById(R.id.collections_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), CollectionsActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.recommendations_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), RecommendationActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.visit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), RequestVisitActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.loan_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), LoanActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.labour_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), LabourRecommendationsActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.pole_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), PoleActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.fertilizer_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), FertilizerActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.payments_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.quickPay_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), QuickPayActivity.class);
                startActivity(intent);
            }
        });
//        view.findViewById(R.id.knowledge_zone).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getContext(), EncyclopediaActivity.class);
//                startActivity(intent);
//            }
//        });
//        view.findViewById(R.id.collection_center).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getContext(), CollectionCenterActivity.class);
//                startActivity(intent);
//            }
//        });
//        view.findViewById(R.id.fertilizer_center).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getContext(), FertilizerCenterActivity.class);
//                startActivity(intent);
//            }
//        });

        albumList = new ArrayList<>();
        prepareAlbums();
//         recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        KnowledgeZoneCategoryAdapter adapter = new KnowledgeZoneCategoryAdapter(getActivity(),albumList);
//        recyclerView.setHasFixedSize(true);
//
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
//      //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(4), true));
//       recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);

        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        final KnowledgeZoneBaseAdapter booksAdapter = new KnowledgeZoneBaseAdapter(getActivity(), albumList);
        gridView.setAdapter(booksAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Album book = albumList.get(position);
               // book.toggleFavorite();

                // This tells the GridView to redraw itself
                // in turn calling your BooksAdapter's getView method again for each cell
                booksAdapter.notifyDataSetChanged();
            }
        });

        dail = (ImageButton)view.findViewById(R.id.dail);

        dail.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {


                // show() method display the toast with message
                // "clicked"
            /*    Toast.makeText(getContext(), "clicked", Toast.LENGTH_LONG)
                        .show();*/

                // Use format with "tel:" and phoneNumber created is
                // stored in u.
                Uri u = Uri.parse("tel:" + "123456789");

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try
                {
                    // Launch the Phone app's dialer with a phone
                    // number to dial a call.
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    // show() method display the toast with
                    // exception message.
                    Toast.makeText(getContext(), "SecurityException", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        return view;
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.encylopedia,
                R.drawable.warehouse,
                R.drawable.warehouse,
                R.drawable.category1,
                R.drawable.category2,
                R.drawable.category3,
                /*  R.drawable.album5,
                  R.drawable.album6,
                  R.drawable.album7,
                  R.drawable.album8,
                  R.drawable.album9,
                  R.drawable.album10,
                  R.drawable.album11*/};
        Album a = new Album("SICKLE" ,  R.string.knowledge_zone,covers[0], "The blade is heavier than that of a normal sickle", "450mm");
        albumList.add(a);

        a = new Album("Grub Hoe",  R.string.collection_center, covers[1], "Digging and Tilling Using a grubbing hoe", "4.25'/1.3 lb");
        albumList.add(a);

        a = new Album("panga",  R.string.fertilizer_center, covers[2],"Convenient access to all your gear", " 5.2 pounds");
        albumList.add(a);

        a = new Album("Rake",  R.string.category1,covers[3], "a long-handled tool with a row of teeth at its head", "58 wagons");
        albumList.add(a);

        a = new Album("SICKLE" ,  R.string.category2,covers[4], "The blade is heavier than that of a normal sickle", "450mm");
        albumList.add(a);

        a = new Album("Grub Hoe",  R.string.category3, covers[5], "Digging and Tilling Using a grubbing hoe", "4.25'/1.3 lb");
        albumList.add(a);
//        a = new Album("SICKLE" ,  R.string.category2,covers[4], "The blade is heavier than that of a normal sickle", "450mm");
//        albumList.add(a);
//
//        a = new Album("Grub Hoe",  R.string.category3, covers[5], "Digging and Tilling Using a grubbing hoe", "4.25'/1.3 lb");
//        albumList.add(a);

       /* a = new Album("Honeymoon",  covers[4]);
        albumList.add(a);
*/
      /*  a = new Album("I Need a Doctor",  covers[5]);
        albumList.add(a);

        a = new Album("Loud",  covers[6]);
        albumList.add(a);

        a = new Album("Legend",  covers[7]);
        albumList.add(a);

        a = new Album("Hello", covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", covers[9]);
        albumList.add(a);*/

        //adapter.notifyDataSetChanged();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add("Menu item")
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("Krishna")
                .setActionView(R.layout.toolbar)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
