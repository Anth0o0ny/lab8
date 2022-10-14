package server;

import baseclasses.Movie;
import baseclasses.MoviesCollection;
import commands.ServerCommand;
import data.processing.MovieProcessing;
import data.processing.UserProcessing;
import interaction.Response;

import sub.StringConstants;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class ServerReceiver {


    private Stack<Movie> collection;
    private final Date creationDate;
    private final MoviesCollection mc;
    private final MovieProcessing movieProcessing = new MovieProcessing();
    private final UserProcessing userProcessing = new UserProcessing();
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public ServerReceiver() {
        mc = new MoviesCollection();
        collection = mc.getCollection();
        creationDate = new Date();

    }

    public Response info() {
        reentrantLock.lock();
        try{
            String[] information = new String[3];
            information[0] = String.valueOf(collection.getClass());
            information[1] = String.valueOf(collection.size());
            information[2] = String.valueOf(creationDate);
            return new Response(information);
        } finally {
            reentrantLock.unlock();
        }
    }

    public Response help(Map<String, ServerCommand> commandMap) {
        return new Response(commandMap.values().stream().map(ServerCommand::getHelp).toArray(String[]::new));
    }


    public Response clear(String login) {
        reentrantLock.lock();
        try{
            if (movieProcessing.clear(login)) {
                collection.removeIf(movie -> movie.getLogin().equals(login));
                return new Response(StringConstants.PatternCommands.RECEIVER_CLEAR_RESULT);
            } else {
                return new Response(StringConstants.Server.WRONG_CLEAR);
            }
        } finally {
           reentrantLock.unlock();
        }
    }




    public Response groupCountingByTagline() {
        reentrantLock.lock();
        try{
            if (collection.isEmpty()) {
                return new Response(StringConstants.PatternCommands.RECEIVER_EMPTY_COLLECTION_RESULT);
            } else {
                ArrayList<String> list = new ArrayList<>();
                for (Movie movie : collection) {
                    list.add(movie.getTagline());
                }
                StringBuilder stringBuilder = new StringBuilder();
                Set<String> st = new HashSet<>(list);
                for (String s : st)
                    stringBuilder.append("\"").append(s).append("\": ").append(Collections.frequency(list, s)).append("\n");

                return new Response(stringBuilder.toString());
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public Response removeById(String argument, String login) {
        reentrantLock.lock();
        try {
            long id;
            try {
                id = Long.parseLong(argument);
            } catch (NumberFormatException e) {
                return new Response(StringConstants.Server.INVALID_ID);
            }


            if (movieProcessing.removeById(id, login)) {
                collection.removeIf(movie -> movie.getId().equals(id));
                return new Response(id + StringConstants.Server.FILM_DELETE_SUCCESS);
            } else {
                return new Response("wrong");
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public Response removeAllByScreenwriter(String arg, String login) {
        reentrantLock.lock();
        try{
            if (movieProcessing.removeAllByScreenwriter(arg, login)) {
                collection.removeIf(movie -> movie.getScreenwriter().getPersonName().equals(arg) &&
                        movie.getLogin().equals(login));
                return new Response(StringConstants.PatternCommands.RECEIVER_REMOVE_ALL_BY_SCREENWRITER_RESULT + arg);
            } else {
                return new Response("wrong");
            }
        } finally {
            reentrantLock.unlock();
        }
    }


    public Response add(Movie movie, String login) {
        reentrantLock.lock();
        try {
            long id = movieProcessing.create(movie, login);
            if (id > 0) {
                movie.setId(id);
                movie.setLogin(login);
                collection.push(movie);
                return new Response(StringConstants.Server.FILM_ADDED + id);
            } else {
                return new Response(StringConstants.Server.WRONG_FILM_ADDED);
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public Response addIfMin(Movie movie, String login) {
        reentrantLock.lock();
        try {
            if (movie.compareTo(Collections.min(collection)) < 0) {

                long id = movieProcessing.create(movie, login);
                movie.setId(id);
                movie.setLogin(login);
                collection.push(movie);
                return new Response(StringConstants.MovieMaking.ADD_SUCCESS);
            } else {
                return new Response(StringConstants.MovieMaking.ADD_FAIL);
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public Response update(String arg, Movie movie, String login) {
        reentrantLock.lock();
        try {
            long id;
            id = Long.parseLong(arg);
//            try {
//
//            } catch (NumberFormatException e) {
//                return new Response(StringConstants.Server.INVALID_ID);
//            }

            if (movieProcessing.update(id, movie, login)){
                collection.removeIf(movieColl -> movieColl.getId().equals(id));
                movie.setId(id);
                movie.setLogin(login);
                collection.push(movie);
                Collections.sort(collection, new SortById());

                return new Response(StringConstants.PatternCommands.RECEIVER_UPDATE_RESULT + id);
            } else {
                return new Response("wrong");
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public Response insertAt(String argument, Movie movie, String login){
        reentrantLock.lock();
        try {
            int index;
            try {
                index = Integer.parseInt(argument) - 1;
            } catch (NumberFormatException e){
                return new Response(StringConstants.Server.INVALID_INDEX);
            }
            if (index < 0 && (collection.size() - index > 0)){
                return new Response(StringConstants.PatternCommands.RECEIVER_INSERT_AT_WRONG_RESULT);
            }else {
                long id = movieProcessing.create(movie, login);
                if (id > 0) {
                    movie.setId(id);
                    movie.setLogin(login);
                    collection.insertElementAt(movie, index);
                    return new Response("фильм с id = " + id + " добавлен в коллекцию на позицию = " + (index + 1) +
                            "\n" + "База данных не поддерживает вставку в конкретную позицию, поэтому в базу данных " +
                            "элемент добавлен последним.");

                } else {
                    return new Response(StringConstants.Server.WRONG_FILM_ADDED);
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }



    public Response shuffle() {
        reentrantLock.lock();
        try{
            Stack<Movie> movies = movieProcessing.readAll();
            Collections.shuffle(movies);
            collection = movies;
            return new Response(movies.toString());
        } finally {
            reentrantLock.unlock();
        }
    }

    void initCollection(){
        collection = movieProcessing.readAll();
        Collections.sort(collection, new SortById());
    }

    public Response getCollection(){
        Response collectResponse = new Response("");

        collectResponse.setCollection(collection);

        return collectResponse;
    }

    public Response authorization(String login, String password) {

        if (login.isEmpty()) {
            return new Response(StringConstants.Server.LOGIN_EMPTY);
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            password = hashtext;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(StringConstants.Server.WRONG_HASH);
        }

        reentrantLock.lock();
        try{
            if (userProcessing.checkExists(login, password)) {
                return new Response("");
            } else if (userProcessing.checkImpostor(login, password)) {
                return new Response("wrong");
            } else {
                userProcessing.create(login, password);
                return new Response("new");
            }
        }finally {
            reentrantLock.unlock();
        }
    }

}





