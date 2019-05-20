package es.deusto.client.controller;

import es.deusto.client.model.ClientModel;
import es.deusto.client.view.ArticleJDialog;
import es.deusto.server.jdo.Article;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ArticleController {
    private ArticleJDialog article;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField bodyField;
    private JLabel titleLabel;
    private JLabel bodyLabel;
    private JTextField titleField;

    private ClientModel model;
    private Article art;

    ArticleController(String title) {
        initComponents();
        initListeners();
        initModel();

        art = model.getArticle(title);

        titleField.setText(art.getTitle());
        bodyField.setText(art.getBody());
    }

    private void initModel() {
        model = ClientModel.getModel();
    }

    private void initComponents() {
        article = new ArticleJDialog();

        titleField = article.getTitleField();
        bodyField = article.getBodyField();
        buttonCancel = article.getButtonCancel();
    }

    void showArticleWindow() {
        //Show landing page
        article.setVisible(true);
    }

    private void initListeners() {
        buttonCancel.addActionListener(this::onCancel);
    }

    private void onCancel(ActionEvent actionEvent) {
        // add your code here
        article.dispose();
    }
}
