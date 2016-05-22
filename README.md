# vertx-tests

When I run the Bootstrap class and open a couple of chrome tabs and simultaneously hit http://localhost:8080,
I see that each of the requests are queued up and only when the first one is completed, the second one picks up.

<pre>
May 22, 2016 1:07:38 AM my.app.RESTServer
INFO: Starting to run the op...
May 22, 2016 1:07:43 AM my.app.RESTServer
INFO: Done and sending the response...
May 22, 2016 1:07:43 AM my.app.RESTServer
INFO: Starting to run the op...
May 22, 2016 1:07:48 AM my.app.RESTServer
INFO: Done and sending the response...
</pre>