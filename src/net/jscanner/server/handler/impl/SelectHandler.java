package net.jscanner.server.handler.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import j2html.tags.ContainerTag;
import net.jscanner.server.handler.RequestHandler;
import net.jscanner.util.FileManager;

import static j2html.TagCreator.*;

/**
 * Represents the "/select" directory on the web server.
 * 
 * @author Desmond Jackson
 */
public class SelectHandler extends RequestHandler {
	
	/**
	 * The HTML to be displayed on this web page.
	 */
	private String HTML = null;

	/**
	 * Builds the HTML to be displayed on this web page.
	 * 
	 * @return The HTML to be displayed on this web page
	 */
	private String buildHtml() {
		if (HTML != null) return HTML;
		Map<String, List<String>> classes = FileManager.getRuntimeClasses();

		ContainerTag html = html();
		html.with(title().withText("Select Threats"));

		ContainerTag head = head();
		head.with(h1().attr("align", "center").withText("Select Threats"));
		head.with(script().withSrc("script.js").withType("text/javascript"));
		html.with(head);

		ContainerTag body = body();
		body.with(text("File To Scan: "), input().withType("file").withId("file"));
		body.with(br(), br());
		body.with(input().withType("button").attr("onclick", "scan();").withValue("SCAN"));
		
		ContainerTag list = ul();
		for (Entry<String, List<String>> entry : classes.entrySet()) {
			String key = entry.getKey();
			ContainerTag listItem = li().with(input().withName("class").withType("checkbox").withValue(key)).with(text(key));
			ContainerTag subList = ul();
			for (String string : entry.getValue())
				subList.with(li().with(input().withName("method").withType("checkbox").withValue(string)).with(text(string)));
			listItem.with(subList);
			list.with(listItem, br());
		}
		body.with(list);
		html.with(body);
		return (HTML = html.render());
	}
	
	@Override
	protected String execute() {
		return buildHtml();
	}

}
