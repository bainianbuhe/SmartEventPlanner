package osu.smartPlanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import android.support.test.rule.ActivityTestRule;
import android.view.View;



public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mActivity = null;


    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        assertNotNull(mActivity.findViewById(R.id.textAddEvent));
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}