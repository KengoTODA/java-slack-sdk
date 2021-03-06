package test_locally.api.model.event;

import com.google.gson.Gson;
import com.slack.api.model.BotIcons;
import com.slack.api.model.event.BotAddedEvent;
import org.junit.Test;
import test_locally.unit.GsonFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BotAddedEventTest {

    @Test
    public void typeName() {
        assertThat(BotAddedEvent.TYPE_NAME, is("bot_added"));
    }

    @Test
    public void deserialize() {
        String json = "{\n" +
                "    \"type\": \"bot_added\",\n" +
                "    \"bot\": {\n" +
                "        \"id\": \"B024BE7LH\",\n" +
                "        \"app_id\": \"A4H1JB4AZ\",\n" +
                "        \"name\": \"hugbot\",\n" +
                "        \"icons\": {\n" +
                "            \"image_48\": \"https:\\/\\/slack.com\\/path\\/to\\/hugbot_48.png\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        BotAddedEvent event = GsonFactory.createSnakeCase().fromJson(json, BotAddedEvent.class);
        assertThat(event.getType(), is("bot_added"));
        assertThat(event.getBot(), is(notNullValue()));
        assertThat(event.getBot().getId(), is("B024BE7LH"));
        assertThat(event.getBot().getAppId(), is("A4H1JB4AZ"));
        assertThat(event.getBot().getName(), is("hugbot"));
        assertThat(event.getBot().getIcons().getImage36(), is(nullValue()));
        assertThat(event.getBot().getIcons().getImage48(), is("https://slack.com/path/to/hugbot_48.png"));
        assertThat(event.getBot().getIcons().getImage72(), is(nullValue()));
    }

    @Test
    public void serialize() {
        Gson gson = GsonFactory.createSnakeCase();
        BotAddedEvent event = new BotAddedEvent();
        event.setBot(new BotAddedEvent.Bot());
        event.getBot().setIcons(new BotIcons());
        event.getBot().getIcons().setImage36("https://slack.com/path/to/hugbot_36.png");
        String generatedJson = gson.toJson(event);
        String expectedJson = "{" +
                "\"type\":\"bot_added\"," +
                "\"bot\":{\"icons\":{\"image_36\":\"https://slack.com/path/to/hugbot_36.png\"}}" +
                "}";
        assertThat(generatedJson, is(expectedJson));
    }
}
