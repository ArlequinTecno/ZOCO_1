// Generated by view binder compiler. Do not edit!
package com.arlequins.zoco_1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.arlequins.zoco_1.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentIndexBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ViewPager2 indexPager;

  @NonNull
  public final TabLayout indexTabs;

  private FragmentIndexBinding(@NonNull ConstraintLayout rootView, @NonNull ViewPager2 indexPager,
      @NonNull TabLayout indexTabs) {
    this.rootView = rootView;
    this.indexPager = indexPager;
    this.indexTabs = indexTabs;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentIndexBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentIndexBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_index, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentIndexBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.index_pager;
      ViewPager2 indexPager = ViewBindings.findChildViewById(rootView, id);
      if (indexPager == null) {
        break missingId;
      }

      id = R.id.index_tabs;
      TabLayout indexTabs = ViewBindings.findChildViewById(rootView, id);
      if (indexTabs == null) {
        break missingId;
      }

      return new FragmentIndexBinding((ConstraintLayout) rootView, indexPager, indexTabs);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
