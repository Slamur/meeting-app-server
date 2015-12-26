<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="model.Participant" %>
<%@ page import="model.MeetingWithSummary" %>
<%@ page import="server.dao.MeetingWithSummaryServiceDao" %>
<%@ page import="server.dao.ParticipantServiceDao" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meetings & Participants admin page</title>
</head>
<body>

<h2>Add meeting</h2>

    <h3>Information</h3>
    <br>
        Title:<input id="title"/>
        Summary:<input id="summary"/>
    <br>
    <br>

    <h3>Start date</h3>
    <br>
        year:<input id="startyear"/>
        month:<input id="startmonth"/>
        day:<input id="startday"/>
        hour:<input id="starthour"/>
        minute:<input id="startminute"/>
    <br>

    <h3>End date</h3>
    <br>
        year:<input id="endyear"/>
        month:<input id="endmonth"/>
        day:<input id="endday"/>
        hour:<input id="endhour"/>
        minute:<input id="endminute"/>
    <br>

    <br>
        priority:<input id="priority"/>
    <br>

<button id="submitMeeting" onclick="addMeeting()">
    Add meeting
</button>

<table title="All meetings" border="2">

    <th>id</th>
    <th>Title</th>
    <th>Summary</th>
    <th>Starts at</th>
    <th>Ends at</th>
    <th>Priority</th>
    <th>Participants</th>

    <%
        DateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        List<MeetingWithSummary> meetings = MeetingWithSummaryServiceDao.getInstance().getItems();
        for (MeetingWithSummary meetingWithSummary : meetings) {
    %>
        <tr>
            <td><%= meetingWithSummary.getId()%>
            </td>
            <td><%= meetingWithSummary.getMeeting().getTitle()%>
            </td>
            <td><%= meetingWithSummary.getSummary()%>
            </td>
            <td><%= sdf.format(meetingWithSummary.getMeeting().getStartDate())%>
            <td><%= sdf.format(meetingWithSummary.getMeeting().getEndDate()) %>
            <td><%= meetingWithSummary.getMeeting().getPriority()%>
            </td>
            <%
                StringBuilder participants = new StringBuilder();
                for (Participant participant : meetingWithSummary.getMeeting().getParticipants()) {
                    participants.append(participant.toString());
                    participants.append(";\t");
                }
            %>
            <td><%= participants.toString()%>
            </td>
        </tr>
    <%
        }
    %>

</table>

<h2>Add participant</h2>

    <h3>Information</h3>
    <br>
        Login:<input id="login"/>
        Password:<input id="password"/>
    <br>
    <br>
        Fio:<input id="fio"/>
        Function:<input id="function"/>
    <br>

<button id="submitParticipant" onclick="addParticipant()">
    Add participant
</button>

<table title="All participants" border="2">

    <th>id</th>
    <th>Login</th>
    <th>Password</th>
    <th>Fio</th>
    <th>Function</th>

    <%
        List<Participant> participants = ParticipantServiceDao.getInstance().getItems();
        for (Participant participant : participants) {
    %>
    <tr>
        <td><%= participant.getId()%>
        </td>
        <td><%= participant.getLogin()%>
        </td>
        <td><%= participant.getPassword()%>
        </td>
        <td><%= participant.getFio()%>
        </td>
        <td><%= participant.getFunction()%>
        </td>
    </tr>
    <%
        }
    %>

</table>
<script type="text/javascript">
    
    function getElementValue(id) {
        return document.getElementById(id).value;
    }

    function getDate(prefix) {
        return getElementValue(prefix + "year") + "-"
                + getElementValue(prefix + "month") + "-"
                + getElementValue(prefix + "day") + " "
                + getElementValue(prefix + "hour") + ":"
                + getElementValue(prefix + "minute");
    }

    function addMeeting() {
        var x = new XMLHttpRequest();

        x.open("PUT", "http://localhost:8080/root/rest/summaries/put/" +
                getElementValue('title') + "/" +
                getElementValue('summary') + "/" +
                getDate('start') + "/" +
                getDate('end') + "/" +
                getElementValue('priority'),
                true);

        x.setRequestHeader("Authorization", "Basic " + btoa("admin" + ":" + "dmina"));
        x.send(null);

        setTimeout(window.location.reload(), 2000);
    }

    function addParticipant() {
        var x = new XMLHttpRequest();

        x.open("PUT", "http://localhost:8080/root/rest/participants/put/" +
                getElementValue('login') + "/" +
                getElementValue('password') + "/" +
                getElementValue('fio') + "/" +
                getElementValue('function'),
                true);

        x.setRequestHeader("Authorization", "Basic " + btoa("admin" + ":" + "dmina"));
        x.send(null);

        setTimeout(window.location.reload(), 2000);
    }

</script>
</body>
</html>

