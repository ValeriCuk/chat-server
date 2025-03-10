package academy.prog;

import jakarta.servlet.http.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/*
    POST(json) -> /add -> AddServlet -> MessageList
    GET -> /get?from=x -> GetListServlet <- MessageList
        <- json[...]
 */

public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();
    private RoomsList rl = RoomsList.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		byte[] buf = requestBodyToArray(req); // json
        String bufStr = new String(buf, StandardCharsets.UTF_8);

		Message msg = Message.fromJSON(bufStr);
		if (msg != null) {
            String movement = req.getParameter("movement");
            configRoom(msg, movement);
            msgList.add(msg);
        }else
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
	}

    private void configRoom(Message msg, String inOutMovement){
        System.out.println(inOutMovement);
        if (inOutMovement == null) return;
        if (inOutMovement.equals("in")) {
            rl.addRoom(msg.getRoom(), msg.getFrom());
        }else if (inOutMovement.equals("out")) {
            rl.leaveRoom(msg.getRoom(), msg.getFrom());
        }
    }

	private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
