package me.riddhimanadib.formmaster.viewholder;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.CompoundButton;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.listener.ReloadListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementCheckbox;

/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public class FormElementCheckBoxViewHolder extends BaseViewHolder {

    public AppCompatTextView mTextViewTitle, mTextViewPositive, mTextViewNegative;
    public AppCompatCheckBox mSwitch;
    private ReloadListener mReloadListener;
    private BaseFormElement mFormElement;
    private FormElementCheckbox mFormElementSwitch;
    private int mPosition;

    public FormElementCheckBoxViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        mTextViewTitle = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
        mTextViewPositive = (AppCompatTextView) v.findViewById(R.id.formElementPositiveText);
        mTextViewNegative = (AppCompatTextView) v.findViewById(R.id.formElementNegativeText);
        mSwitch = (AppCompatCheckBox) v.findViewById(R.id.formElementSwitch);
        mReloadListener = reloadListener;
    }

    @Override
    public void bind(final int position, BaseFormElement formElement, final Context context) {
        mFormElement = formElement;
        mPosition = position;
        mFormElementSwitch = (FormElementCheckbox) mFormElement;

        mTextViewTitle.setText(mFormElementSwitch.getTitle());
        mTextViewPositive.setText(mFormElementSwitch.getPositiveText());
        mTextViewPositive.setVisibility(null != mFormElementSwitch.getPositiveText() ? View.VISIBLE : View.GONE);
        mTextViewPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mFormElementSwitch.getClickListener()) {
                    mFormElementSwitch.getClickListener().onTextClicked(mFormElementSwitch.getTag());
                }
            }
        });
        mTextViewNegative.setHint(mFormElementSwitch.getNegativeText());
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mReloadListener.updateValue(position, b ? "1" : "0");
            }
        });
    }

}
