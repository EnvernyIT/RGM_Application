package com.bludots.app.rgm.password.registration.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "token/expired")
@PageTitle("RGM | Token Expired")
@CssImport(value = "./styles/components/styling2.css")
public class NoTokenErrorView extends VerticalLayout{
	
	public NoTokenErrorView() {
		addClassName("error-view");
		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);
		setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
		
		VerticalLayout tokenInvalidMessage = new VerticalLayout(
				new H1("Error Page - Invalid Token"),
				new H3("You have been redirected to this error page."),
				new Paragraph("This page is to indicate that the given token has expired or has not been activated. Register a request through email to either update the token or make a new token.")
				);
		
		tokenInvalidMessage.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);
		tokenInvalidMessage.setAlignItems(FlexComponent.Alignment.CENTER);
		tokenInvalidMessage.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
		tokenInvalidMessage.addClassName("error-message-form");
		
		add(
				tokenInvalidMessage
				);

	}

}
