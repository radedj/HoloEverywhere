package com.WazaBe.HoloDemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.WazaBe.HoloDemo.fragments.AboutFragment;
import com.WazaBe.HoloDemo.fragments.MainFragment;
import com.WazaBe.HoloDemo.fragments.PreferenceFragment;
import com.WazaBe.HoloEverywhere.ThemeManager;
import com.WazaBe.HoloEverywhere.app.Fragment;
import com.WazaBe.HoloEverywhere.sherlock.SActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;

public class DemoActivity extends SActivity {
	private final class FragmentListener implements TabListener {
		private final Class<? extends Fragment> clazz;
		private Fragment fragment;

		public FragmentListener(Class<? extends Fragment> clazz) {
			this.clazz = clazz;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {

		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (fragment == null) {
				try {
					fragment = clazz.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			ft.replace(android.R.id.content, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {

		}
	}

	private void addTab(Class<? extends Fragment> clazz, String title) {
		Tab tab = getSupportActionBar().newTab();
		tab.setText(title);
		tab.setTabListener(new FragmentListener(clazz));
		getSupportActionBar().addTab(tab);
	}

	public void closeCalendar(View v) {
		Utils.closeCalendar(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setForceThemeApply(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content);
		if (isABSSupport()) {
			getSupportActionBar().setDisplayShowTitleEnabled(false);
			getSupportActionBar().setDisplayShowHomeEnabled(false);
			getSupportActionBar().setNavigationMode(
					ActionBar.NAVIGATION_MODE_TABS);
			addTab(MainFragment.class, "Holo Demo");
			addTab(PreferenceFragment.class, "Settings");
			addTab(AboutFragment.class, "About");
		} else {
			Utils.replaceFragment(android.R.id.content,
					MainFragment.getInstance(), this);
		}
	}

	public void setDarkTheme(View v) {
		ThemeManager.restartWithTheme(this, ThemeManager.DARK);
	}

	public void setLightTheme(View v) {
		ThemeManager.restartWithTheme(this, ThemeManager.LIGHT);
	}

	public void showAlertDialog(View v) {
		Utils.showAlertDialog(this);
	}

	public void showCalendar(View v) {
		Utils.showCalendar(this);
	}

	public void showDatePicker(View v) {
		Utils.showDatePicker(this);
	}

	public void showNumberPicker(View v) {
		Utils.showNumberPicker(this);
	}

	public void showProgressDialog(View v) {
		Utils.showProgressDialog(this);
	}

	public void showTimePicker(View v) {
		Utils.showTimePicker(this);
	}

	public void showToast(View v) {
		Utils.showToast(this);
	}
}