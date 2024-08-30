package lessons.java.common;

import java.util.List;
import java.util.Scanner;

public class PageLoop {
    final AppView view;

    public PageLoop(AppView view) {
        this.view = view;
    }

    public void run() {
        while (true) {
            view.action();
            displayMenu();

            Scanner in = new Scanner(System.in);
            int value = in.nextInt();

            if (handleUserInput(value)) {
                break;
            }
        }
    }

    private boolean handleUserInput(int value) {
        if (value < 1 || value > getMenuOptions()) {
            System.err.println("Неверное значение. Повторите ввод");
            return false;
        } else if (value == getMenuOptions()) {
            view.nowPage = 0;
            return true;
        } else if (value <= view.children.size()) {
            AppView selectedView = view.children.get(value - 1);
            new PageLoop(selectedView).run();
        } else if (value <= view.children.size() + view.getComparatorOptions().size()) {
            int comparatorIndex = value - view.children.size() - 1;
            view.selectedComparator = view.availableComparators.get(comparatorIndex);
            new PageLoop(view).run();
            return true;
        } else if (value == getMenuOptions() - (view.hasNextPage ? 2 : 1) && view.nowPage > 0) {
            view.nowPage--;
            new PageLoop(view).run();
            return true;
        } else if (value == getMenuOptions() - 1 && view.hasNextPage) {
            view.nowPage++;
            new PageLoop(view).run();
            return true;
        }
        return false;
    }

    private int getMenuOptions() {
        int options = view.children.size() + view.getComparatorOptions().size();
        options += view.nowPage > 0 ? 1 : 0;
        options += view.hasNextPage ? 1 : 0;
        options++;
        return options;
    }

    void displayMenu() {
        System.out.println("\n" + view.title);
        System.out.println("Выберите вариант:");

        for (int i = 0; i < view.children.size(); i++) {
            AppView _view = view.children.get(i);
            System.out.println((i + 1) + " - " + _view.title);
        }

        List<String> comparatorOptions = view.getComparatorOptions();
        for (int i = 0; i < comparatorOptions.size(); i++) {
            System.out.println((view.children.size() + 1 + i) + " - " + comparatorOptions.get(i));
        }

        if (view.nowPage > 0) {
            System.out.println(getMenuOptions() - (view.hasNextPage ? 2 : 1) + " - Пред. страница");
        }

        if (view.hasNextPage) {
            System.out.println((getMenuOptions() - 1) + " - Следующая страница");
        }

        if (view.title.equals("Магазин")) {
            System.out.println((getMenuOptions()) + " - Выход из магазина");
        } else {
            System.out.println((getMenuOptions()) + " - В меню");
        }
    }
}