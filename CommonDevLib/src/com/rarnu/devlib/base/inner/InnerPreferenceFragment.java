package com.rarnu.devlib.base.inner;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuInflater;

import com.rarnu.devlib.base.intf.InnerIntf;
import com.rarnu.devlib.common.UIInstance;

public abstract class InnerPreferenceFragment extends PreferenceFragment
		implements InnerIntf {

	protected String tagText;
	protected String tabTitle;

	@Override
	public String getTagText() {
		return tagText;
	}

	public String getTabTitle() {
		return tabTitle;
	}

	public InnerPreferenceFragment() {
		super();
	}

	public InnerPreferenceFragment(String tagText, String tabTitle) {
		super();
		this.tagText = tagText;
		this.tabTitle = tabTitle;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initComponents();
		initEvents();
		initLogic();
	}

	protected abstract int getBarTitle();

	protected abstract int getBarTitleWithPath();

	protected abstract String getCustomTitle();

	protected abstract void initComponents();

	protected abstract void initEvents();

	protected abstract void initLogic();

	protected abstract void initMenu(Menu menu);

	protected abstract String getMainActivityName();

	protected abstract int getPreferenceLayoutId();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		addPreferencesFromResource(getPreferenceLayoutId());

		if (getActivity().getActionBar() != null) {
			if (getCustomTitle() == null || getCustomTitle().equals("")) {
				if (UIInstance.dualPane) {
					getActivity().getActionBar()
							.setTitle(getBarTitleWithPath());
				} else {
					getActivity().getActionBar().setTitle(getBarTitle());
				}
			} else {
				getActivity().getActionBar().setTitle(getCustomTitle());
			}
		}
	}

//	@Override
//	public void onPause() {
//		if (getActivity().getActionBar() != null) {
//			if (getCustomTitle() == null || getCustomTitle().equals("")) {
//				getActivity().getActionBar().setTitle(getBarTitle());
//			} else {
//				getActivity().getActionBar().setTitle(getCustomTitle());
//			}
//		}
//		super.onPause();
//	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (getActivity() == null) {
			return;
		}
		if (getActivity().getClass().getName().equals(getMainActivityName())
				&& !UIInstance.dualPane) {
			return;
		}

		initMenu(menu);
	}

}
