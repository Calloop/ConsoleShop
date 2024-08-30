package lessons.java.view;

import lessons.java.common.AppView;

import java.util.ArrayList;

public class MainView extends AppView {
    public MainView(ArrayList<AppView> children) {
        super("Магазин", children);
    }
}