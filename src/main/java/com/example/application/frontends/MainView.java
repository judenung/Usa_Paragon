package com.example.application.frontends;

import com.example.application.tools.MyUI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@PageTitle("PFSAT")
public class MainView extends VerticalLayout {
    BuildView buildView;

    MainView (@Autowired MyUI UI) {
        addClassName("main-view");
        setSizeFull();
        getUI().ifPresent(ui -> ui.navigate("cross-section"));

        buildView = new BuildView(UI);
        add(buildView);

    }
}
