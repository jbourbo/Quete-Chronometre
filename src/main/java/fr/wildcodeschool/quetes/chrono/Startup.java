package fr.wildcodeschool.quetes.chrono;

public class Startup {

    public static void main(String... args) throws InterruptedException {

        boolean isFullScreen = false;
        long startingTime = 0;

        if (args.length >= 1){
            isFullScreen = Boolean.parseBoolean(args[0]);
            if (args.length >= 2){
                try{
                    startingTime = Long.parseLong(args[1]);
                    startingTime /= 1000L;
                }
                catch(NumberFormatException nfe){
                    nfe.printStackTrace();
                    startingTime = 0;

                }
            }
        }

        TimeProvider tp = new ValidTimeProvider(startingTime);
        new Chrono(tp, isFullScreen).roll();
    }

}