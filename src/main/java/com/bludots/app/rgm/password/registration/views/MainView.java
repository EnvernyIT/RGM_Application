package com.bludots.app.rgm.password.registration.views;

import com.bludots.app.rgm.password.registration.ContextProvider;
import com.bludots.app.rgm.password.registration.services.RequestService;
import com.bludots.app.rgm.password.registration.valueObjects.RequestVO;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "")
@PageTitle("RGM | Email")
@CssImport(value = "./styles/components/styling.css")
public class MainView extends VerticalLayout {

	private EmailField email;
	private Button sendEmail;

	public MainView() {
		super();
		addClassName("validate-view");
		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);

		email = new EmailField("Email");
		email.setClearButtonVisible(true);
		email.setPattern("");
		email.setPlaceholder("example@company.com");
		email.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
		email.setHelperText("Enter an email address");
		email.setPattern("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
		email.setWidth("350px");

		sendEmail = new Button("Send Email");
		sendEmail.addClickShortcut(Key.ENTER);
		sendEmail.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		sendEmail.addClassName("send-button");

		setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

		VerticalLayout validateForm = new VerticalLayout(new H1("Rosebel Gold Mines"), email, sendEmail);
		validateForm.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);
		validateForm.setAlignItems(FlexComponent.Alignment.CENTER);
		validateForm.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
		validateForm.addClassName("validate-form");

		sendEmail.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				RequestService requestService = ContextProvider.getBean(RequestService.class);
				RequestVO requestVO = new RequestVO();
				requestVO.setEmail(email.getValue());

				if (email.getValue().equals(null) || email.getValue().equals("")) {
					// notification class van VAADIN
					Notification notification = new Notification("Please add email address!", 3000,
							Position.TOP_CENTER);
					notification.open();
				}  
				
				if (!email.getValue().equals(null) || !email.getValue().equals("")) {
					requestService.persistRequest(requestVO);
					Notification notification = new Notification("Email has ben sent. Check your inbox.", 4000,
							Position.TOP_CENTER);
					email.clear();
					notification.open();
				}
								
			}
		});

		add(validateForm);

	}
}
