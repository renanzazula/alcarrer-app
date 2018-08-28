package com.alcarrer.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.alcarrer.model.BreadCrumb;

public class Util {

	public static String FormadaDataSql(java.sql.Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * Metodo responsavel por obeter a data atula do systema "dd/MM/yyyy" - para
	 * data HH:mm:ss - Hora
	 * 
	 * @param formato
	 * @return
	 */
	public static String getDataAtualFormatada(String formato) {
		SimpleDateFormat formatador = new SimpleDateFormat(formato);
		Date date = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return formatador.format(cal.getTime());
	}

	public static Double somaValores(Double valor1, Double valor2) {
		return Double.sum(valor1, valor2);
	}

	/**
	 * Metodo devolvo lista breadCrumb
	 * 
	 * @param statusCaixa
	 * @return
	 */
	public static List<BreadCrumb> breadCrumbList(MessageSource message, List<String> listMessage) {

		List<BreadCrumb> breadCrumb = new ArrayList<BreadCrumb>();

		BreadCrumb home = new BreadCrumb();
		home.setTexto(message.getMessage("global.home", null, Locale.US));
		home.setLink("index");
		home.setLast("FALSE");
		breadCrumb.add(home);

		for (int i = 0; i < listMessage.size(); i++) {
			BreadCrumb menu = new BreadCrumb();
			menu.setTexto(message.getMessage(listMessage.get(i), null, Locale.US));
			menu.setLink("#");
			if (listMessage.size() == i + 1) {
				menu.setLast("TRUE");
			} else {
				menu.setLast("FALSE");
			}
			breadCrumb.add(menu);
		}
		return breadCrumb;
	}

}
