// Generated by view binder compiler. Do not edit!
package com.arlequins.zoco_1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.arlequins.zoco_1.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSettingsBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final TextView changePasswdTextView;

  @NonNull
  public final Chip languageChip;

  @NonNull
  public final Chip lightingModeChip;

  @NonNull
  public final TextInputEditText settingsEmailEditText;

  @NonNull
  public final TextInputLayout settingsEmailTextInputLayout;

  @NonNull
  public final TextInputEditText settingsNameEditText;

  @NonNull
  public final TextInputLayout settingsNameTextInputLayout;

  @NonNull
  public final TextInputEditText settingsPhoneEditText;

  @NonNull
  public final TextInputLayout settingsPhoneTextInputLayout;

  @NonNull
  public final ImageView settingsProfilePictureImageView;

  @NonNull
  public final Button settingsSave;

  private FragmentSettingsBinding(@NonNull ScrollView rootView,
      @NonNull TextView changePasswdTextView, @NonNull Chip languageChip,
      @NonNull Chip lightingModeChip, @NonNull TextInputEditText settingsEmailEditText,
      @NonNull TextInputLayout settingsEmailTextInputLayout,
      @NonNull TextInputEditText settingsNameEditText,
      @NonNull TextInputLayout settingsNameTextInputLayout,
      @NonNull TextInputEditText settingsPhoneEditText,
      @NonNull TextInputLayout settingsPhoneTextInputLayout,
      @NonNull ImageView settingsProfilePictureImageView, @NonNull Button settingsSave) {
    this.rootView = rootView;
    this.changePasswdTextView = changePasswdTextView;
    this.languageChip = languageChip;
    this.lightingModeChip = lightingModeChip;
    this.settingsEmailEditText = settingsEmailEditText;
    this.settingsEmailTextInputLayout = settingsEmailTextInputLayout;
    this.settingsNameEditText = settingsNameEditText;
    this.settingsNameTextInputLayout = settingsNameTextInputLayout;
    this.settingsPhoneEditText = settingsPhoneEditText;
    this.settingsPhoneTextInputLayout = settingsPhoneTextInputLayout;
    this.settingsProfilePictureImageView = settingsProfilePictureImageView;
    this.settingsSave = settingsSave;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_settings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSettingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.change_passwd_text_view;
      TextView changePasswdTextView = ViewBindings.findChildViewById(rootView, id);
      if (changePasswdTextView == null) {
        break missingId;
      }

      id = R.id.language_chip;
      Chip languageChip = ViewBindings.findChildViewById(rootView, id);
      if (languageChip == null) {
        break missingId;
      }

      id = R.id.lighting_mode_chip;
      Chip lightingModeChip = ViewBindings.findChildViewById(rootView, id);
      if (lightingModeChip == null) {
        break missingId;
      }

      id = R.id.settings_email_edit_text;
      TextInputEditText settingsEmailEditText = ViewBindings.findChildViewById(rootView, id);
      if (settingsEmailEditText == null) {
        break missingId;
      }

      id = R.id.settings_email_text_input_layout;
      TextInputLayout settingsEmailTextInputLayout = ViewBindings.findChildViewById(rootView, id);
      if (settingsEmailTextInputLayout == null) {
        break missingId;
      }

      id = R.id.settings_name_edit_text;
      TextInputEditText settingsNameEditText = ViewBindings.findChildViewById(rootView, id);
      if (settingsNameEditText == null) {
        break missingId;
      }

      id = R.id.settings_name_text_input_layout;
      TextInputLayout settingsNameTextInputLayout = ViewBindings.findChildViewById(rootView, id);
      if (settingsNameTextInputLayout == null) {
        break missingId;
      }

      id = R.id.settings_phone_edit_text;
      TextInputEditText settingsPhoneEditText = ViewBindings.findChildViewById(rootView, id);
      if (settingsPhoneEditText == null) {
        break missingId;
      }

      id = R.id.settings_phone_text_input_layout;
      TextInputLayout settingsPhoneTextInputLayout = ViewBindings.findChildViewById(rootView, id);
      if (settingsPhoneTextInputLayout == null) {
        break missingId;
      }

      id = R.id.settings_profile_picture_image_view;
      ImageView settingsProfilePictureImageView = ViewBindings.findChildViewById(rootView, id);
      if (settingsProfilePictureImageView == null) {
        break missingId;
      }

      id = R.id.settings_save;
      Button settingsSave = ViewBindings.findChildViewById(rootView, id);
      if (settingsSave == null) {
        break missingId;
      }

      return new FragmentSettingsBinding((ScrollView) rootView, changePasswdTextView, languageChip,
          lightingModeChip, settingsEmailEditText, settingsEmailTextInputLayout,
          settingsNameEditText, settingsNameTextInputLayout, settingsPhoneEditText,
          settingsPhoneTextInputLayout, settingsProfilePictureImageView, settingsSave);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
