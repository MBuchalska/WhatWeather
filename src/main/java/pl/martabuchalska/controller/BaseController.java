package pl.martabuchalska.controller;

import pl.martabuchalska.view.ViewFactory;

public class BaseController {
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = "/"+fxmlName;  //we have to add / because it sits in resource folder
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
