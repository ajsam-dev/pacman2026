package dk.sdu.imada.oop26.controller_layer;

import dk.sdu.imada.oop26.view_layer.StartpageView;
import dk.sdu.imada.oop26.view_layer.ViewHandler;

public class StartPageController {
  private ViewHandler handler;
  private StartpageView view;

   
  public StartPageController(ViewHandler handler, StartpageView view){
    this.handler = handler;
    this.view = view;
    handleEvents();
  }

  public void init(){
    handleEvents();
  }
  
  public void handleEvents(){
    on_enter_button();
  }

  public void on_enter_button(){
    view.getEnterButton().setOnAction(event -> {
      handler.openPacmanScreen();
    });
  }

  
}
