package main;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controller {
    @FXML
    private Pane pane;
    @FXML
    private Canvas canvas;

    private Figure figure;

    private View view;

    @FXML
    public void initialize() {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());
        canvas.widthProperty().addListener(e -> draw());
        canvas.heightProperty().addListener(e -> draw());
        figure = new Figure();
        view = new View(canvas);
    }

    private void draw() {
        view.draw(figure);
    }

    public void handleKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case LEFT:
                view.moveFigure(-5, 0);
                break;
            case RIGHT:
                view.moveFigure(5, 0);
                break;
            case UP:
                view.moveFigure(0, -5);
                break;
            case DOWN:
                view.moveFigure(0, 5);
                break;
            case EQUALS:
                view.scaleFigure(1.1);
                break;
            case MINUS:
                view.scaleFigure(1 / 1.1);
                break;
            case Q:
                view.rotateFigure(10);
                break;
            case W:
                view.rotateFigure(-10);
                break;
        }
        draw();
    }

    public void mouseClick(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        view.setCxCy(x,y);
        draw();
    }
}
