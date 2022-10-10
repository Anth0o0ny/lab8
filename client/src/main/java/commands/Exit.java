package commands;

import client.ClientReceiver;
import interaction.Request;
import sub.StringConstants;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Exit extends ClientCommand{
    public Exit(ClientReceiver clientReceiver){
        super(clientReceiver);
    }

    @Override
    public Optional<Request> execute(String arg) {
        Optional<Request> request = clientReceiver.exit();
        return Optional.empty();

    }
}
