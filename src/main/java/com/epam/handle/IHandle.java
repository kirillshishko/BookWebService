package com.epam.handle;


import com.epam.methods.Request;
import com.epam.methods.Response;

import java.io.IOException;

public interface IHandle {

	void handle(Request rq, Response rp) throws IOException;

}
