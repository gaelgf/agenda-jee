<html>
<body>
<div class="planner" id="planner"><%= getPlanner(request) %></div>
<%@ page import="com.dhtmlx.planner.*,com.dhtmlx.planner.data.*" %>
<%@ page import="com.dhtmlx.planner.controls.DHXAgendaView" %>
<%!
           String getPlanner(HttpServletRequest request) throws Exception {
           DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
           DHXAgendaView view = new DHXAgendaView();
		   s.views.add(view);
           s.localizations.set("fr");
           s.setWidth(900);
           s.setInitialDate(2016, 0, 20);
           s.load("events.jsp", DHXDataFormat.JSON);
           s.data.dataprocessor.setURL("events.jsp");
           return s.render();
}
%>
</body>
</html>