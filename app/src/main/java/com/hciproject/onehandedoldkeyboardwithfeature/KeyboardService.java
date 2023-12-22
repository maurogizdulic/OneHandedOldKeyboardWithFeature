package com.hciproject.onehandedoldkeyboardwithfeature;

import android.annotation.SuppressLint;
import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.List;


public class KeyboardService extends InputMethodService {

    private boolean isSwapped = false;
    private int indexABC = -1;
    private int indexDEF = -1;
    private int indexGHI = -1;
    private int indexJKL = -1;
    private int indexMNO = -1;
    private int indexPQRS = -1;
    private int indexTUV = -1;
    private int indexWXYZ = -1;
    private int indexSigns = -1;
    private int click = -1;
    private boolean enteredSpace = false;
    private long countInIf = 0;
    private long oldTime = 0;
    private boolean clickInIf = false;
    private boolean otherClickInIf = false;
    private long elapsedTimeABC = 0;
    private long elapsedTimeDEF = 0;
    private long lastKeyPressTimeABC = 0;
    private long lastKeyPressTimeDEF = 0;
    private long currentTimeABC = System.currentTimeMillis();
    private long currentTimeDEF = System.currentTimeMillis();

    private final Handler handler = new Handler();
    private static final int LETTER_THRESHOLD = 1000; // 1 seconds

    // Key codes for letters in ABC
    private int[] currentKeyCodesSigns = {46, 44, 33, 63};
    private int[] currentKeyCodesABC = {97, 98, 99};
    // Key codes for letters in DEF
    private int[] currentKeyCodesDEF = {100, 101, 102};
    private int[] currentKeyCodesGHI = {103, 104, 105};
    private int[] currentKeyCodesJKL = {106, 107, 108};
    private int[] currentKeyCodesMNO = {109, 110, 111};
    private int[] currentKeyCodesPQRS = {112, 113, 114, 115};
    private int[] currentKeyCodesTUV = {116, 117, 118};
    private int[] currentKeyCodesWXYZ = {119, 120, 121, 122};
    List<String> listOfPrintLetters = new ArrayList<>();
    List<Integer> listOfButtonValues = new ArrayList<>();
    private KeyEvent keyEventBackspace = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL);
    private KeyEvent keyEventSpace = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE);
    private KeyEvent keyEventEnter = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER);


    @Override
    public View onCreateInputView(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View keyboardView = inflater.inflate(R.layout.activity_main, null);
        InputConnection inputConnection = getCurrentInputConnection();
        Time time = new Time();
        Letter letter = new Letter();

        // Find buttons by ID and set click listeners
        Button button1 = keyboardView.findViewById(R.id.button1);
        Button button2 = keyboardView.findViewById(R.id.button2);
        Button button3 = keyboardView.findViewById(R.id.button3);
        Button button4 = keyboardView.findViewById(R.id.button4);
        Button button5 = keyboardView.findViewById(R.id.button5);
        Button button6 = keyboardView.findViewById(R.id.button6);
        Button button7 = keyboardView.findViewById(R.id.button7);
        Button button8 = keyboardView.findViewById(R.id.button8);
        Button button9 = keyboardView.findViewById(R.id.button9);
        Button button10 = keyboardView.findViewById(R.id.button10);
        Button button11 = keyboardView.findViewById(R.id.button11);
        Button button12 = keyboardView.findViewById(R.id.button12);
        Button button13 = keyboardView.findViewById(R.id.button13);
        TextView textView = keyboardView.findViewById(R.id.textView);
        LinearLayout verticalLinearLayout = keyboardView.findViewById(R.id.vertical_linear_layout);
        LinearLayout horizontalLinearLayout = keyboardView.findViewById(R.id.horizontal_linear_layout);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ConstraintLayout constraintLayout = keyboardView.findViewById(R.id.constraint_layout);

        while(listOfButtonValues.size() != 0){
            listOfButtonValues.remove(0);
        }

        while(listOfPrintLetters.size() != 0){
            listOfPrintLetters.remove(0);
        }

        listOfButtonValues.add(0);

        handler.post(new Runnable() {
            @Override
            public void run() {

                /*
                // Setting stop time to measure time difference between click time and this time
                time.setStopMeasure(System.currentTimeMillis());

                // Calculate time difference
                time.setTimeDifference(time.getStopMeasure() - time.getStartMeasure());
                Log.d("TIME DIFFERENCE", String.valueOf(time.getTimeDifference()));

                // Setting previous time beacuse on new click time starts to be measured again
                time.setPrevTimeDifference(time.getTimeDifference());

                /* I wanted to see the values of the list
                for (Integer button : listOfButtonValues) {
                    Log.d("LIST BUTTON VALUE", String.valueOf(button));
                }
                 */

                if(click == 10){
                    click = 0;

                    time.setTimeDifference(0);
                    time.setPrevTimeDifference(-1);

                    enteredSpace = true;

                    Log.d("SIZE OF BUTTON LIST", String.valueOf(listOfButtonValues.size()));
                    while(listOfButtonValues.size() != 0){
                        listOfButtonValues.remove(0);
                    }

                    Log.d("SIZE OF LETTER LIST", String.valueOf(listOfPrintLetters.size()));
                    while(listOfPrintLetters.size() != 0){
                        listOfPrintLetters.remove(0);
                    }

                    Log.d("TAG", "VRTI MI SPACE U PETLJI");
                    listOfButtonValues.add(0);
                    //Log.d("SPACE-BACKSPACE-ENTER", String.valueOf(listOfPrintLetters.get(0)));

                }
                else if (click == 11){
                    click = 0;
                    enteredSpace = true;

                    InputConnection inputConnection = getCurrentInputConnection();
                    inputConnection.sendKeyEvent(keyEventSpace);
                    inputConnection.closeConnection();

                    Log.d("SIZE OF BUTTON LIST", String.valueOf(listOfButtonValues.size()));
                    while(listOfButtonValues.size() != 0){
                        listOfButtonValues.remove(0);
                    }

                    Log.d("SIZE OF LETTER LIST", String.valueOf(listOfPrintLetters.size()));
                    while(listOfPrintLetters.size() != 0){
                        listOfPrintLetters.remove(0);
                    }

                    listOfButtonValues.add(0);

                }
                else if(click == 12){
                    click = 0;
                    enteredSpace = true;

                    InputConnection inputConnection = getCurrentInputConnection();
                    inputConnection.sendKeyEvent(keyEventEnter);
                    inputConnection.closeConnection();

                    Log.d("SIZE OF BUTTON LIST", String.valueOf(listOfButtonValues.size()));
                    while(listOfButtonValues.size() != 0){
                        listOfButtonValues.remove(0);
                    }

                    Log.d("SIZE OF LETTER LIST", String.valueOf(listOfPrintLetters.size()));
                    while(listOfPrintLetters.size() != 0){
                        listOfPrintLetters.remove(0);
                    }

                    listOfButtonValues.add(0);
                }
                else{

                    // Setting stop time to measure time difference between click time and this time
                    time.setStopMeasure(System.currentTimeMillis());

                    // Calculate time difference
                    time.setTimeDifference(time.getStopMeasure() - time.getStartMeasure());
                    Log.d("TIME DIFFERENCE", String.valueOf(time.getTimeDifference()));

                    // Setting previous time beacuse on new click time starts to be measured again
                    time.setPrevTimeDifference(time.getTimeDifference());

                    // If the list has an initial value of 0 and if the size of the list is greater than or equal to 2, I delete the initial 0. Necessary because of the initial value.
                    if(listOfButtonValues.get(0) == 0 && listOfButtonValues.size() >= 2){
                        listOfButtonValues.remove(0);
                    }

                    // If current click is same as previous (that means that we click on same button)
                    Log.d("CURRENT AND LIST CLICK", String.valueOf(letter.getCurrentClick()) + "==" + listOfButtonValues.get(0));
                    if(letter.getCurrentClick() == listOfButtonValues.get(0) || letter.getCurrentClick() == -1){
                        Log.d("ENTERED IFF", "WEEEEEEEEEEE ENTEREDDDDDDD IFFFFFFFF");
                        if (clickInIf == true) {
                            //time.setPrevTimeDifference(oldTime);
                            oldTime = time.getTimeDifference();
                            otherClickInIf = true;
                        }

                        if(listOfButtonValues.size() >= 2){
                            //Log.d("1QUEUE BUTTON NUMBER ", String.valueOf(queueButtonNumber.peek()));
                            listOfButtonValues.remove(0);
                            //Log.d("2QUEUE BUTTON NUMBER ", String.valueOf(queueButtonNumber.peek()));
                        }


                        // If the size of the list is greater than or equal to 2, remove letter
                        if(listOfPrintLetters.size() >= 2){
                            listOfPrintLetters.remove(0);
                        }

                        // If time difference is greater than LETTER_THRESHOLD and we click on some button, print that value
                        Log.d("TIME DIFFERENCE IN IF", String.valueOf(time.getTimeDifference()));
                        Log.d("CLICK VALUE", String.valueOf(click));
                        if(time.getTimeDifference() > LETTER_THRESHOLD && click == 1){

                            InputConnection inputConnection = getCurrentInputConnection();
                            inputConnection.commitText(letter.getLetterForPrint(), 1);
                            Log.d("TAG", "PRINT IN IF: " + letter.getLetterForPrint());
                            textView.setText("");
                            clickInIf = true;
                            oldTime = time.getTimeDifference();
                            inputConnection.closeConnection();
                            letter.setClick(false);
                            click = 0;
                            indexSigns = -1;
                            indexABC = -1;
                            indexDEF = -1;
                            indexGHI = -1;
                            indexJKL = -1;
                            indexMNO = -1;
                            indexPQRS = -1;
                            indexTUV = -1;
                            indexWXYZ = -1;
                        }
                    }
                    // If we current click is different than previous check if we print letter before
                    else {
                        Log.d("ENTERED ELSE", "WEEEEEEEEEEE ENTEREDDDDDDD ELSEEEEEEEEEE");

                        // If we print letter before, set value to false and set real prev time difference

                        if (clickInIf == true) {
                            time.setPrevTimeDifference(oldTime);
                            clickInIf = false;
                        }

                        if(otherClickInIf == true){
                            time.setPrevTimeDifference(oldTime);
                            otherClickInIf = false;
                        }

                        // If size of list is greater than or equal to 2, remove first value
                        if (listOfButtonValues.size() >= 2) {
                            //Log.d("1QUEUE BUTTON NUMBER ", String.valueOf(queueButtonNumber.peek()));
                            listOfButtonValues.remove(0);
                            //Log.d("2QUEUE BUTTON NUMBER ", String.valueOf(queueButtonNumber.peek()));
                        }

                        if(!enteredSpace){
                            // We check if we haven't printed the previous letter, in case we haven't, we print the previous letter and start with the current one
                            if (time.getPrevTimeDifference() <= LETTER_THRESHOLD && time.getPrevTimeDifference() >= 0) {
                                InputConnection inputConnection = getCurrentInputConnection();
                                inputConnection.commitText(listOfPrintLetters.get(0), 1);
                                Log.d("PRINT IN ELSE", listOfPrintLetters.get(0));

                                inputConnection.setComposingText(letter.getLetterForPrint(), 1);
                                inputConnection.closeConnection();

                                letter.setClick(false);

                                if (letter.getCurrentClick() == 1){
                                    indexSigns = 0;
                                    indexABC = -1;
                                    indexDEF = -1;
                                    indexGHI = -1;
                                    indexJKL = -1;
                                    indexMNO = -1;
                                    indexPQRS = -1;
                                    indexTUV = -1;
                                    indexWXYZ = -1;
                                }  else if (letter.getCurrentClick() == 2) {
                                    indexABC = 0;
                                    indexDEF = -1;
                                    indexGHI = -1;
                                    indexJKL = -1;
                                    indexMNO = -1;
                                    indexPQRS = -1;
                                    indexTUV = -1;
                                    indexWXYZ = -1;
                                } else if (letter.getCurrentClick() == 3) {
                                    indexABC = -1;
                                    indexDEF = 0;
                                    indexGHI = -1;
                                    indexJKL = -1;
                                    indexMNO = -1;
                                    indexPQRS = -1;
                                    indexTUV = -1;
                                    indexWXYZ = -1;
                                } else if (letter.getCurrentClick() == 4) {
                                    indexABC = -1;
                                    indexDEF = -1;
                                    indexGHI = 0;
                                    indexJKL = -1;
                                    indexMNO = -1;
                                    indexPQRS = -1;
                                    indexTUV = -1;
                                    indexWXYZ = -1;
                                } else if (letter.getCurrentClick() == 5) {
                                    indexABC = -1;
                                    indexDEF = -1;
                                    indexGHI = -1;
                                    indexJKL = 0;
                                    indexMNO = -1;
                                    indexPQRS = -1;
                                    indexTUV = -1;
                                    indexWXYZ = -1;
                                } else if (letter.getCurrentClick() == 6) {
                                    indexABC = -1;
                                    indexDEF = -1;
                                    indexGHI = -1;
                                    indexJKL = -1;
                                    indexMNO = 0;
                                    indexPQRS = -1;
                                    indexTUV = -1;
                                    indexWXYZ = -1;
                                } else if (letter.getCurrentClick() == 7) {
                                    indexABC = -1;
                                    indexDEF = -1;
                                    indexGHI = -1;
                                    indexJKL = -1;
                                    indexMNO = -1;
                                    indexPQRS = 0;
                                    indexTUV = -1;
                                    indexWXYZ = -1;
                                } else if (letter.getCurrentClick() == 8) {
                                    indexABC = -1;
                                    indexDEF = -1;
                                    indexGHI = -1;
                                    indexJKL = -1;
                                    indexMNO = -1;
                                    indexPQRS = -1;
                                    indexTUV = 0;
                                    indexWXYZ = -1;
                                } else if (letter.getCurrentClick() == 9) {
                                    indexABC = -1;
                                    indexDEF = -1;
                                    indexGHI = -1;
                                    indexJKL = -1;
                                    indexMNO = -1;
                                    indexPQRS = -1;
                                    indexTUV = -1;
                                    indexWXYZ = 0;
                                }
                                click = 0;
                            }
                        }

                        if (listOfPrintLetters.size() >= 2) {
                            listOfPrintLetters.remove(0);
                        }

                        // Setting previous click same as current
                        letter.setPreviousClick(letter.getCurrentClick());
                    }
                }

                handler.postDelayed(this, 1);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start measure time on click -> Use for handler.post(new Runnable)
                time.setStartMeasure(System.currentTimeMillis());

                // Setting true if I click on button
                letter.setClick(true);
                click = 1;
                enteredSpace = false;

                // Setting what button is clicked
                letter.setCurrentClick(1);
                listOfButtonValues.add(1);

                indexSigns = (indexSigns + 1) % currentKeyCodesSigns.length;
                Log.d("INDEX ABC", String.valueOf(indexSigns));

                letter.setLetterForPrint(String.valueOf((char) currentKeyCodesSigns[indexSigns]));
                listOfPrintLetters.add(letter.getLetterForPrint());
                Log.d("COMPOSE ABC", String.valueOf(letter.getLetterForPrint()));
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.setComposingText(letter.getLetterForPrint(), 1);
                inputConnection.closeConnection();

                textView.setText(String.valueOf(letter.getLetterForPrint()));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start measure time on click -> Use for handler.post(new Runnable)
                time.setStartMeasure(System.currentTimeMillis());

                // Setting true if I click on button
                letter.setClick(true);
                click = 1;
                enteredSpace = false;

                // Setting what button is clicked
                letter.setCurrentClick(2);
                listOfButtonValues.add(2);

                indexABC = (indexABC + 1) % currentKeyCodesABC.length;
                Log.d("INDEX ABC", String.valueOf(indexABC));

                letter.setLetterForPrint(String.valueOf((char) currentKeyCodesABC[indexABC]));
                listOfPrintLetters.add(letter.getLetterForPrint());
                Log.d("COMPOSE ABC", String.valueOf(letter.getLetterForPrint()));
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.setComposingText(letter.getLetterForPrint(), 1);
                inputConnection.closeConnection();

                textView.setText(String.valueOf(letter.getLetterForPrint()));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start measure time on click -> Use for handler.post(new Runnable)
                time.setStartMeasure(System.currentTimeMillis());

                // Setting true if I click on button
                letter.setClick(true);
                click = 1;
                enteredSpace = false;

                // Setting what button is clicked
                letter.setCurrentClick(3);
                listOfButtonValues.add(3);

                indexDEF = (indexDEF + 1) % currentKeyCodesDEF.length;
                Log.d("INDEX DEF", String.valueOf(indexDEF));

                letter.setLetterForPrint(String.valueOf((char) currentKeyCodesDEF[indexDEF]));
                listOfPrintLetters.add(letter.getLetterForPrint());
                Log.d("COMPOSE DEF", String.valueOf(letter.getLetterForPrint()));
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.setComposingText(letter.getLetterForPrint(), 1);
                inputConnection.closeConnection();

                textView.setText(String.valueOf(letter.getLetterForPrint()));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start measure time on click -> Use for handler.post(new Runnable)
                time.setStartMeasure(System.currentTimeMillis());

                // Setting true if I click on button
                letter.setClick(true);
                click = 1;
                enteredSpace = false;

                // Setting what button is clicked
                letter.setCurrentClick(4);
                listOfButtonValues.add(4);

                indexGHI = (indexGHI + 1) % currentKeyCodesGHI.length;
                Log.d("INDEX GHI", String.valueOf(indexGHI));

                letter.setLetterForPrint(String.valueOf((char) currentKeyCodesGHI[indexGHI]));
                listOfPrintLetters.add(letter.getLetterForPrint());
                Log.d("COMPOSE GHI", String.valueOf(letter.getLetterForPrint()));
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.setComposingText(letter.getLetterForPrint(), 1);
                inputConnection.closeConnection();

                textView.setText(String.valueOf(letter.getLetterForPrint()));
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start measure time on click -> Use for handler.post(new Runnable)
                time.setStartMeasure(System.currentTimeMillis());

                // Setting true if I click on button
                letter.setClick(true);
                click = 1;
                enteredSpace = false;

                // Setting what button is clicked
                letter.setCurrentClick(5);
                listOfButtonValues.add(5);

                indexJKL = (indexJKL + 1) % currentKeyCodesJKL.length;
                Log.d("INDEX JKL", String.valueOf(indexJKL));

                letter.setLetterForPrint(String.valueOf((char) currentKeyCodesJKL[indexJKL]));
                listOfPrintLetters.add(letter.getLetterForPrint());
                Log.d("COMPOSE JKL", String.valueOf(letter.getLetterForPrint()));
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.setComposingText(letter.getLetterForPrint(), 1);
                inputConnection.closeConnection();

                textView.setText(String.valueOf(letter.getLetterForPrint()));
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start measure time on click -> Use for handler.post(new Runnable)
                time.setStartMeasure(System.currentTimeMillis());

                // Setting true if I click on button
                letter.setClick(true);
                click = 1;
                enteredSpace = false;

                // Setting what button is clicked
                letter.setCurrentClick(6);
                listOfButtonValues.add(6);

                indexMNO = (indexMNO + 1) % currentKeyCodesMNO.length;
                Log.d("INDEX MNO", String.valueOf(indexMNO));

                letter.setLetterForPrint(String.valueOf((char) currentKeyCodesMNO[indexMNO]));
                listOfPrintLetters.add(letter.getLetterForPrint());
                Log.d("COMPOSE MNO", String.valueOf(letter.getLetterForPrint()));
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.setComposingText(letter.getLetterForPrint(), 1);
                inputConnection.closeConnection();

                textView.setText(String.valueOf(letter.getLetterForPrint()));
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start measure time on click -> Use for handler.post(new Runnable)
                time.setStartMeasure(System.currentTimeMillis());

                // Setting true if I click on button
                letter.setClick(true);
                click = 1;
                enteredSpace = false;

                // Setting what button is clicked
                letter.setCurrentClick(7);
                listOfButtonValues.add(7);

                indexPQRS = (indexPQRS + 1) % currentKeyCodesPQRS.length;
                Log.d("INDEX PQRS", String.valueOf(indexPQRS));

                letter.setLetterForPrint(String.valueOf((char) currentKeyCodesPQRS[indexPQRS]));
                listOfPrintLetters.add(letter.getLetterForPrint());
                Log.d("COMPOSE PQRS", String.valueOf(letter.getLetterForPrint()));
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.setComposingText(letter.getLetterForPrint(), 1);
                inputConnection.closeConnection();

                textView.setText(String.valueOf(letter.getLetterForPrint()));
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start measure time on click -> Use for handler.post(new Runnable)
                time.setStartMeasure(System.currentTimeMillis());

                // Setting true if I click on button
                letter.setClick(true);
                click = 1;
                enteredSpace = false;

                // Setting what button is clicked
                letter.setCurrentClick(8);
                listOfButtonValues.add(8);

                indexTUV = (indexTUV + 1) % currentKeyCodesTUV.length;
                Log.d("INDEX TUV", String.valueOf(indexTUV));

                letter.setLetterForPrint(String.valueOf((char) currentKeyCodesTUV[indexTUV]));
                listOfPrintLetters.add(letter.getLetterForPrint());
                Log.d("COMPOSE TUV", String.valueOf(letter.getLetterForPrint()));
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.setComposingText(letter.getLetterForPrint(), 1);
                inputConnection.closeConnection();

                textView.setText(String.valueOf(letter.getLetterForPrint()));
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start measure time on click -> Use for handler.post(new Runnable)
                time.setStartMeasure(System.currentTimeMillis());

                // Setting true if I click on button
                letter.setClick(true);
                click = 1;
                enteredSpace = false;

                // Setting what button is clicked
                letter.setCurrentClick(9);
                listOfButtonValues.add(9);

                indexWXYZ = (indexWXYZ + 1) % currentKeyCodesWXYZ.length;
                Log.d("INDEX WXYZ", String.valueOf(indexWXYZ));

                letter.setLetterForPrint(String.valueOf((char) currentKeyCodesWXYZ[indexWXYZ]));
                listOfPrintLetters.add(letter.getLetterForPrint());
                Log.d("COMPOSE WXYZ", String.valueOf(letter.getLetterForPrint()));
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.setComposingText(letter.getLetterForPrint(), 1);
                inputConnection.closeConnection();

                textView.setText(String.valueOf(letter.getLetterForPrint()));
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.sendKeyEvent(keyEventBackspace);
                inputConnection.closeConnection();

                indexABC = -1;
                indexDEF = -1;
                indexGHI = -1;
                indexJKL = -1;
                indexMNO = -1;
                indexPQRS = -1;
                indexTUV = -1;
                indexWXYZ = -1;

                click = 10;

                textView.setText("");
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.finishComposingText();
                inputConnection.closeConnection();

                Log.d("TAG", "STISNUO SAM SPACE");

                indexABC = -1;
                indexDEF = -1;
                indexGHI = -1;
                indexJKL = -1;
                indexMNO = -1;
                indexPQRS = -1;
                indexTUV = -1;
                indexWXYZ = -1;

                click = 11;

                textView.setText("");
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.finishComposingText();
                inputConnection.sendKeyEvent(keyEventEnter);
                inputConnection.closeConnection();

                indexABC = -1;
                indexDEF = -1;
                indexGHI = -1;
                indexJKL = -1;
                indexMNO = -1;
                indexPQRS = -1;
                indexTUV = -1;
                indexWXYZ = -1;

                click = 12;

                textView.setText("");
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSwapped){
                    horizontalLinearLayout.removeAllViews();
                    horizontalLinearLayout.addView(textView);
                    horizontalLinearLayout.addView(verticalLinearLayout);
                    isSwapped = true;
                }
                else{
                    horizontalLinearLayout.removeAllViews();
                    horizontalLinearLayout.addView(verticalLinearLayout);
                    horizontalLinearLayout.addView(textView);
                    isSwapped = false;
                }
            }
        });

        return keyboardView;
    }

}
