package com.example.vishnubk.sharjahcharity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vishnubk.sharjahcharity.Fragments.ItemFragment.OnListFragmentInteractionListener;
import com.example.vishnubk.sharjahcharity.Models.Post;
import com.example.vishnubk.sharjahcharity.R;
import com.example.vishnubk.sharjahcharity.ui.DetailPageActivity;
import com.example.vishnubk.sharjahcharity.ui.DonatorDetailActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {


    private final static int HEADER_VIEW = 0;
    private final static int CONTENT_VIEW = 1;
    SwipeAdapter adapter;

    private List<Post> post = new ArrayList<Post>();
    //private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    int ID;

    private Context context;

    public MyItemRecyclerViewAdapter(OnListFragmentInteractionListener listener, int id, Context contexts) {
        //mValues = items;
        ID = id;
        mListener = listener;
        context=contexts;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.list_item_post, parent, false);
        int layoutRes = 0;
        switch (viewType) {
            case HEADER_VIEW:
                layoutRes = R.layout.list_item_header;
                break;
            case CONTENT_VIEW:
                layoutRes = R.layout.list_item_post;
                break;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public int getItemViewType(int position) {
        switch(position) {
            case 0:
                return HEADER_VIEW;
            default:
                return CONTENT_VIEW;
        }
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);
//        if(holder.viewFlipper != null) {
//            adapter = new SwipeAdapter(context);
//            holder.viewFlipper.setAdapter(adapter);
//            holder.indicator.setViewPager(holder.viewFlipper);
//        }

        switch (holder.getItemViewType()) {
            case 0:
                adapter = new SwipeAdapter(context);
            holder.viewFlipper.setAdapter(adapter);
            holder.indicator.setViewPager(holder.viewFlipper);
                break;
            default:
                if (ID == 1) {
                    holder.textView.setText(R.string.charity1);
                    holder.imageView.setImageResource(R.drawable.b);

                } else if (ID == 2) {
                    holder.textView.setText(R.string.charity2);
                    holder.imageView.setImageResource(R.drawable.a);

                } else if (ID == 3) {
                    holder.textView.setText(R.string.charity3);
                    holder.imageView.setImageResource(R.drawable.c);

                }

                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("vikz", position + "hh");
                        Post item = new Post();
                        Bitmap anImage = ((BitmapDrawable) holder.imageView.getDrawable().getCurrent()).getBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        anImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        // String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), anImage, "Title", null);
                        item.setImage(byteArray);
                        item.setTitle(String.valueOf(holder.textView.getText()));
                        post.add(item);
                        Intent intent = new Intent(context, DetailPageActivity.class);
                        intent.putExtra("title", post.get(post.size() - 1).getTitle());
                        intent.putExtra("image", post.get(post.size() - 1).getImage());
                        context.startActivity(intent);
                    }
                });

                holder.donateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DonatorDetailActivity.class);
                        context.startActivity(intent);
                    }
                });

                holder.shareButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                        sendIntent.setType("text/plain");
                        context.startActivity(Intent.createChooser(sendIntent, "Share"));
                    }
                });

                break;
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return 7;
        // return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        public final View mView;
        public final ImageView imageView;
        public final TextView textView;
        public CardView cardView;
        RecyclerView recyclerView;
        ImageView donateButton;
        ImageView shareButton;
        ViewPager viewFlipper;
        CircleIndicator indicator;
       ;
        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            mView = view;
            imageView = (ImageView) view.findViewById(R.id.image);
            textView = (TextView) view.findViewById(R.id.title);
            cardView = (CardView) view.findViewById(R.id.card_view);
            recyclerView = (RecyclerView) view.findViewById(R.id.list);
            donateButton = (ImageView) view.findViewById(R.id.donate_button);
            shareButton=(ImageView)view.findViewById(R.id.share_button);
            viewFlipper = (ViewPager)view.findViewById(R.id.flipper);
            indicator = (CircleIndicator)view.findViewById(R.id.indicator);
        }
    }

    public class SwipeAdapter extends PagerAdapter {

        private int[] images = {R.drawable.charity,R.drawable.charity2,R.drawable.charity3};
        private Context ctx;
        private LayoutInflater inflater;

        public SwipeAdapter(Context ctx){
            this.ctx = ctx;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view ==(LinearLayout)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.swipe,container,false);
            ImageView img =(ImageView)v.findViewById(R.id.imageView);

            img.setImageResource(images[position]);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            container.refreshDrawableState();
        }
    }
}