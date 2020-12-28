package com.example.sportup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog_messgae extends AppCompatDialogFragment {
    private Context activity;
    private Tranier tranier;

    public Dialog_messgae(Context activity , Tranier t) {
        this.activity = activity;
        this.tranier=t;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        builder.setTitle("INFORMATION").setMessage("No Mesaage To Display :(").setPositiveButton("RETURN HOME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(activity,trainer_Home.class);
                intent.putExtra("trainer",tranier);
                startActivity(intent);
            }
        });
        return builder.create();

    }
}
