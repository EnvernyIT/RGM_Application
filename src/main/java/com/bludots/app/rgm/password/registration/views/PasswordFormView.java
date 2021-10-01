package com.bludots.app.rgm.password.registration.views;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

import com.bludots.app.rgm.password.registration.ContextProvider;
import com.bludots.app.rgm.password.registration.repositories.entities.Request;
import com.bludots.app.rgm.password.registration.services.CredentialsService;
import com.bludots.app.rgm.password.registration.services.RequestService;
import com.bludots.app.rgm.password.registration.valueObjects.CredentialsVO;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

@Route(value = "registration")
@PageTitle("RGM | Registration")
@CssImport(value = "./styles/components/styling2.css")
public class PasswordFormView extends VerticalLayout implements HasUrlParameter<String> {

	// Right Field
	private EmailField email;
	private PasswordField password;
	private PasswordField confirmPassword;
	private Button register;

	public PasswordFormView() {
		super();
	}

	// Check if token exists
	private String token;
	private Request request;

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {

		Location location = event.getLocation();
		QueryParameters queryParameters = location.getQueryParameters();

		Map<String, List<String>> parametersMap = queryParameters.getParameters();

		token = parametersMap.get("token").toString().substring(1, parametersMap.get("token").toString().length() - 1);

		RequestService requestService = ContextProvider.getBean(RequestService.class);
		request = requestService.findRequest(token);

		if (request != null) {
			LocalDateTime expireTime = request.getRequestDateTime().plusMinutes(1);
			if (expireTime.isAfter(LocalDateTime.now())) {
				buildPasswordFormView();
			} else {
				event.forwardTo("token/expired");
//				UI.getCurrent().navigate(NoTokenErrorView.class);
//				UI.getCurrent().navigate("token/expired");
			}
		} else {
			event.forwardTo("token/expired");
//			UI.getCurrent().navigate(NoTokenErrorView.class);
		}
	}

	public void buildPasswordFormView() {
		addClassName("register-view");
		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);

		email = new EmailField("Email");
		email.setClearButtonVisible(true);
		email.setPlaceholder("example@company.com");
		email.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
		email.setWidth("350px");
		email.setValue(request.getEmail());

		password = new PasswordField("Password");
		confirmPassword = new PasswordField("Confirm Password");
		password.setWidth("350px");
		confirmPassword.setWidth("350px");

		register = new Button("Register");
		register.addClickShortcut(Key.ENTER);
		register.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		register.addClassName("register-button");

		Dialog dialog = new Dialog();
		dialog.setWidth("400px");
		dialog.setHeight("150px");
		dialog.setCloseOnEsc(false);
		dialog.setCloseOnOutsideClick(false);
		Button confirmButton = new Button("Okay", event -> {
			dialog.close();
		});

		setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

		VerticalLayout registrationForm = new VerticalLayout(new H1("Rosebel Gold Mines"), new H2("Registration"),
				email, password, confirmPassword, register);
		registrationForm.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);
		registrationForm.setAlignItems(FlexComponent.Alignment.CENTER);
		registrationForm.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
		registrationForm.addClassName("register-form");

		register.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				CredentialsService credentialsService = ContextProvider.getBean(CredentialsService.class);
				CredentialsVO credentialsVO = new CredentialsVO();

				if (request.getEmail().equals(email.getValue())) {
					credentialsVO.setEmail(email.getValue());
					if (password.getValue().equals(confirmPassword.getValue())) {
						credentialsVO.setPassword(password.getValue());
						credentialsVO.setConfirmPassword(password.getValue());
						credentialsService.persistCredentials(credentialsVO);
						dialog.add(new Text("Email and password have been registered!"), new Div(confirmButton));
						dialog.open();
					} else if (!password.getValue().equals(confirmPassword)) {
						dialog.add(new Text("Confirm Password not identical to password!"), new Div(confirmButton));
						dialog.open();
					}
					email.clear();
					password.clear();
					confirmPassword.clear();
				} else {
					dialog.add(new Text("Email not identical to email to which this url has been sent. Please enter correct email!"), new Div(confirmButton));
					dialog.open();
				}
			}
		});

		add(registrationForm);

	}

}
