package pl.edu.pk.ztp.graf.test;

import org.junit.Test;

import pl.edu.pk.ztp.graf.Graph;
import pl.edu.pk.ztp.graf.Main;

import static org.fest.assertions.Assertions.*;

public class AwfulTest {

    @Test
    public void shouldHandleIt() throws Exception {
        Main.GraphBuilder builder = new Main.GraphBuilder();
        final Graph graph = builder.fromFile(getClass().getResource("/test.txt").getFile(), 10000);
        assertThat(graph.getNumberOfConsistentComponents()).isEqualTo(799);
    }

    @Test
    public void shouldHandleItToo() throws Exception {
        Main.GraphBuilder builder = new Main.GraphBuilder();
        final Graph graph = builder.fromFile(getClass().getResource("/grap_13").getFile(), 13);
        assertThat(graph.getNumberOfConsistentComponents()).isEqualTo(4);
    }

    @Test
    public void shouldFindAloneVertex() throws Exception {
        Main.GraphBuilder builder = new Main.GraphBuilder();
        final Graph graph = builder.fromFile(getClass().getResource("/grap_13").getFile(), 13);
        assertThat(graph.hasAnyEdgesStartingAt(6)).isFalse();
    }

    @Test
    public void shouldReturnProperNumberOfEdges() throws Exception {
        Main.GraphBuilder builder = new Main.GraphBuilder();
        final Graph graph = builder.fromFile(getClass().getResource("/grap_13").getFile(), 13);
        assertThat(graph.getNumberOfEdges()).isEqualTo(10);
    }

}


