/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package alticast.com.androidtvtestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * MainActivity class that loads {@link MainFragment}.
 */
public class MainActivity extends Activity {

    private ListView lsvTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lsvTest = (ListView) findViewById(R.id.lsvTest);
        String[] listOfTest = getResources().getStringArray(R.array.list_of_test_case);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfTest);
        lsvTest.setAdapter(adapter);

        lsvTest.setOnItemClickListener(new ItemClickListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TestCase.SCAN_TEST) {
            if (resultCode == RESULT_OK) {
                boolean isSuccess = data.getBooleanExtra(TestCase.TEST_RESULT, false);
                if (isSuccess) {
                    lsvTest.getChildAt(requestCode).setBackground(getResources().getDrawable(R.drawable.success_row_background));
                } else {
                    lsvTest.getChildAt(requestCode).setBackground(getResources().getDrawable(R.drawable.fail_row_background));
                }

            }
        }
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            switch (position) {
                case TestCase.SCAN_TEST:
                    Intent intent = new Intent(getApplication(), ScanActivity.class);
                    startActivityForResult(intent,  TestCase.SCAN_TEST);
                    break;
                case TestCase.RECORDING_TEST:
                    break;
                case TestCase.BLOCKING_PORT_TEST:
                    break;
                default:
                    break;
            }
        }
    }
}
