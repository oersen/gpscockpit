package com.mirfatif.mylocation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.mirfatif.mylocation.databinding.AboutDialogBinding;

public class AboutDialogFragment extends AppCompatDialogFragment {

  private AboutDialogFragment() {}

  private MainActivity mA;

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    mA = (MainActivity) getActivity();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AboutDialogBinding b = AboutDialogBinding.inflate(mA.getLayoutInflater());
    b.version.setText(BuildConfig.VERSION_NAME);
    openWebUrl(b.sourceCode, R.string.source_url);

    AlertDialog dialog = new Builder(mA).setView(b.getRoot()).create();
    return Utils.setDialogBg(dialog);
  }

  private void openWebUrl(View view, int linkResId) {
    view.setOnClickListener(v -> Utils.openWebUrl(mA, getString(linkResId)));
  }

  private void sendShareIntent() {
    Intent intent = new Intent(Intent.ACTION_SEND).setType("text/plain");
    intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
    String text = getString(R.string.share_text, getString(R.string.play_store_url));
    startActivity(Intent.createChooser(intent.putExtra(Intent.EXTRA_TEXT, text), null));
  }

  public static void show(FragmentActivity activity) {
    new AboutDialogFragment().show(activity.getSupportFragmentManager(), "ABOUT");
  }
}
