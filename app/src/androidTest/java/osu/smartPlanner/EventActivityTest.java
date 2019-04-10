package osu.smartPlanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.ActivityTestRule;


public class EventActivityTest {


    @Rule
    public ActivityTestRule<EventActivity> mActivityTestRule = new ActivityTestRule<>(EventActivity.class);
    private EventActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();

    }

    @Test
    public void testLaunch() {
        assertNotNull(mActivity.findViewById(R.id.eventTittle));
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}