package com.example.application.views.about;

import java.util.Vector;

import javax.validation.constraints.NotNull;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.gridpro.EditColumnConfigurator;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.gridpro.ItemUpdater;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;


@Route(value = "about")
public class AboutView extends VerticalLayout {

    public AboutView() {
        GridPro<Foo> grid = new GridPro<>();
        // Grid<Foo> grid = new Grid<>();
        grid.addColumn(Foo::getBar).setHeader("Bar");


        final Select<String> stringSelect = new Select<>();
        stringSelect.getElement().getThemeList().add("grid-pro-editor");
        Vector<String>values = new Vector<>();
        values.add("eins");
        values.add("zwei");
        values.add("drei");
        values.add("vier");

        Vector<String>values2 = new Vector<>();
        values2.add("one");
        values2.add("two");
        values2.add("three");
        values2.add("four");
        values2.add("five");

        stringSelect.setItems(values);

        ValueProvider<Foo, String> valueProv = new ValueProvider<Foo, String>() {
            private static final long serialVersionUID = 4384938746145675867L;

            @Override
            public String apply(Foo source) {
                return source.getBar();
            }
        };

        ItemUpdater<Foo,String>itemUpdater = new ItemUpdater<Foo, String>() {

            private static final long serialVersionUID = -4715860794955863805L;

            @Override
            public void accept(Foo item, String newValue) {
                item.setBar(newValue);
            }
        };

        EditColumnConfigurator<Foo> editCol =  grid.addEditColumn(valueProv);
        editCol.custom(stringSelect, itemUpdater);

        EditColumnConfigurator<Foo> editCol2 =  grid.addEditColumn(valueProv);
        editCol2.select(itemUpdater, values2);

        Vector<Foo>testItems = new Vector<>();
        testItems.add(new Foo("Click me"));
        testItems.add(new Foo("Click you"));
        testItems.add(new Foo("Click us"));

        ListDataProvider<String> myDataProvider = new ListDataProvider<>(values);
        stringSelect.setDataProvider(myDataProvider);

        Button b = new Button("Switch Items " , e -> {
            if(switchVar) {
                stringSelect.setItems(values);
                switchVar=false;
            }else{
                stringSelect.setItems(values2);
                switchVar=true;
            }
        });

        add(grid, b);

        grid.setItems(testItems);
    }

    boolean switchVar = false;

    private static class Foo {

        private String bar;

        private Foo(@NotNull String bar) {
            this.bar = bar;
        }

        public String getBar() {
            return bar;
        }

        public void setBar(String newValue) {
            this.bar = newValue;
        }
    }
}
