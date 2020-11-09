package org.zalando.logbook.httpclient;

import com.google.gag.annotation.remark.Facepalm;
import com.google.gag.annotation.remark.OhNoYouDidnt;
import org.apache.http.HttpResponse;
import org.apache.http.nio.ContentDecoder;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.protocol.HttpAsyncResponseConsumer;
import org.apache.http.protocol.HttpContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@OhNoYouDidnt
@Facepalm
@ExtendWith(MockitoExtension.class)
final class ForwardingHttpAsyncResponseConsumerTest<T> {

    @Mock
    private HttpAsyncResponseConsumer<T> delegate;

    private final HttpAsyncResponseConsumer<T> unit = new ForwardingHttpAsyncResponseConsumer<T>() {

        @Override
        protected HttpAsyncResponseConsumer<T> delegate() {
            return delegate;
        }

    };

    private final HttpResponse response = mock(HttpResponse.class);
    private final ContentDecoder decoder = mock(ContentDecoder.class);
    private final IOControl control = mock(IOControl.class);
    private final HttpContext context = mock(HttpContext.class);

    @Test
    void testResponseReceived() throws Exception {
        unit.responseReceived(response);
        verify(delegate).responseReceived(response);
    }

    @Test
    void testConsumeContent() throws Exception {
        unit.consumeContent(decoder, control);
        verify(delegate).consumeContent(decoder, control);
    }

    @Test
    void testResponseCompleted() throws Exception {
        unit.responseCompleted(context);
        verify(delegate).responseCompleted(context);
    }

    @Test
    void testCancel() throws Exception {
        unit.cancel();
        verify(delegate).cancel();
    }

    @Test
    void testIsDone() throws Exception {
        unit.isDone();
        verify(delegate).isDone();
    }

    @Test
    void testGetResult() throws Exception {
        unit.getResult();
        verify(delegate).getResult();
    }

    @Test
    void testFailed() throws Exception {
        final IOException e = new IOException();
        unit.failed(e);
        verify(delegate).failed(e);
    }

    @Test
    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    void testGetException() throws Exception {
        unit.getException();
        verify(delegate).getException();
    }

    @Test
    void testClose() throws Exception {
        unit.close();
        verify(delegate).close();
    }

}
