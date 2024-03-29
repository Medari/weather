package org.cisco.cidemo.web;

import org.cisco.cidemo.weather.model.Weather;
import org.cisco.cidemo.weather.persist.WeatherDAO;
import org.cisco.cidemo.weather.WeatherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class WeatherController implements Controller {

	private WeatherService weatherService;
	private WeatherDAO weatherDAO;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String zip = request.getParameter("zip");
		Weather weather = weatherService.retrieveForecast(zip);
		weatherDAO.save(weather);
		return new ModelAndView("weather", "weather", weather);
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public WeatherDAO getWeatherDAO() {
		return weatherDAO;
	}

	public void setWeatherDAO(WeatherDAO weatherDAO) {
		this.weatherDAO = weatherDAO;
	}
}