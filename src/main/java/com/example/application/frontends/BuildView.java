package com.example.application.frontends;

import com.example.application.tools.IconStyle;
import com.example.application.tools.InputFrame;
import com.example.application.tools.MyUI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

public class BuildView extends AppLayout {

    private InputFrame IF;
    public Tabs tabs;

    public BuildView (@Autowired MyUI UI) {

        IF = UI.getInputFrame();
        UI.buildView = this;

        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("Plane Frame Structural Analysis Tool");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");

        addToNavbar(toggle, title);
        tabs = new Tabs();
        refreshTabs(UI);
    }

    private Tab createTab (VaadinIcon viewIcon, String viewName, Class c) {
        RouterLink link = new RouterLink();
        link.add(IconStyle.setStyle1(viewIcon), new Span(viewName));
        link.setRoute(c);

        return new Tab(link);
    }

    public void refreshTabs (MyUI UI) {
        remove(tabs);

        int i = tabs.getSelectedIndex();
        if ((IF.frameMembers.isEmpty() && IF.isDefaultCS) || (IF.crossSections.isEmpty() && !IF.isDefaultCS)) {
            if (IF.isDefaultCS) {
                i = 1;
            }
            else {
                i = 0;
            }
        }

        tabs.removeAll();
        Tab tab1 = createTab(VaadinIcon.VIEWPORT, "Cross Section", CrossSectionView.class);
        Tab tab2 = createTab(VaadinIcon.LINE_H,"Frame Member", FrameMemberView.class);
        Tab tab3 = createTab(VaadinIcon.EJECT,"Support", SupportView.class);
        Tab tab4 = createTab(VaadinIcon.ADD_DOCK,"Joint Load", JointLoadView.class);
        Tab tab5 = createTab(VaadinIcon.DOWNLOAD_ALT,"Point Load", PointLoadView.class);
        Tab tab6 = createTab(VaadinIcon.BAR_CHART,"Linearly Distributed Load", LinearlyDistributedLoadView.class);
        Tab tab7 = createTab(VaadinIcon.FIRE, "Temperature Change", TemperatureChangeView.class);
        Tab tab8 = createTab(VaadinIcon.WRENCH, "Fabrication Error", FabricationErrorView.class);
        Tab tab9 = createTab(VaadinIcon.WARNING, "Support Settlement", SupportSettlementView.class);
        Tab tab10 = createTab(VaadinIcon.PLAY, "Solution", SolutionView.class);

        tab1.setEnabled(!IF.isDefaultCS);
        tab2.setEnabled(IF.isDefaultCS || (!IF.isDefaultCS && !IF.crossSections.isEmpty()));
        tab3.setEnabled(!IF.frameMembers.isEmpty());
        tab4.setEnabled(!IF.frameMembers.isEmpty());
        tab5.setEnabled(!IF.frameMembers.isEmpty());
        tab6.setEnabled(!IF.frameMembers.isEmpty());
        tab7.setEnabled(!IF.frameMembers.isEmpty());
        tab8.setEnabled(!IF.frameMembers.isEmpty());
        tab9.setEnabled(!IF.supports.isEmpty());
        tab10.setEnabled(!IF.frameMembers.isEmpty() && !IF.supports.isEmpty() &&
                (!IF.jointLoads.isEmpty() || !IF.pointLoads.isEmpty() || !IF.linearlyDistributedLoads.isEmpty()));

        tabs.add(
                tab1,
                tab2,
                tab3,
                tab4,
                tab5,
                tab6,
                tab7,
                tab8,
                tab9,
                tab10
        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setSelectedIndex(i);

        addToDrawer(tabs);
    }

}
