package com.forcetower.playtime.utils;

import android.graphics.Color;

import com.forcetower.playtime.db.model.Cast;
import com.forcetower.playtime.db.model.Comment;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.relations.TitleWatchlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by João Paulo on 31/05/2018.
 */
public class MockUtils {
    private static final Title avengers = new Title("Avengers: Infinity War", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg", "https://youtu.be/6ZfuNTqbHE8", 4.2f, "13/05/2018");
    private static final Title deadpool = new Title("Deadpool 2", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/to0spRl1CMDvyUbOnbb4fTk3VAd.jpg", "https://youtu.be/D86RtevtfrA", 4.1f, "13/08/2018");
    private static final Title solo = new Title("Han Solo: Uma História Star Wars", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/lJChjsNR7O1waqsOIqlkePoGtZJ.jpg", "https://youtu.be/EqKajgmcs4M", 3.5f, "23/05/2018");
    private static final Title devolucoes = new Title("Não Se Aceitam Devoluções", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/91WqHNXe5tKmLI1pQnptZV9BDkW.jpg", "https://youtu.be/mekjCq7wzEE", 3.0f, "13/08/2018");
    private static final Title gameNight = new Title("A Noite do Jogo", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/fWbGZt8URdS8N4AVpTRupdBXClz.jpg", "https://youtu.be/2wSTtajHAXg", 3.8f, "24/09/2018");
    private static final Title gnomeu = new Title("Gnomeu e Julieta: O Mistério do Jardim", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/b6ZZriobpdpkAy8hfkNENaykot2.jpg", "https://youtu.be/0RbKk3HSXok", 2.8f, "13/05/2016");
    private static final Title cock = new Title("Não Vai Dar", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/cx0LpfM6Drla8uzFfMT09uqKPRu.jpg", "https://youtu.be/g1WVgyh5ah4", 3.8f, "03/05/2018");

    private static final Title readyP1 = new Title("Jogador Nº 1", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/9xDD53hohCMp5VoFtqNPfzTsnLJ.jpg", "https://youtu.be/q_1OJNcTld0", 4.0f, "13/09/2018");
    private static final Title jumanji = new Title("Jumanji: Bem-Vindo à Selva", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rHPGCpqsbg3WK90uig0KbxBtFrh.jpg", "https://youtu.be/y0oqJGwi42M", 3.9f, "13/05/2018");
    private static final Title blade = new Title("Blade Runner 2049", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/zppVzoeQvds6FPTzcMww6chPIks.jpg", "https://youtu.be/OEW3gbptBZg", 4.0f, "01/01/2020");
    private static final Title kingsman = new Title("Kingsman: Serviço Secreto", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wDhy1Ga9BoLwkHOI0jJISQem28v.jpg", "https://youtu.be/pm68xWnBeFg", 4.1f, "06/04/2019");
    private static final Title maze = new Title("Maze Runner: A Cura Mortal", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/4H1vAQHD1rojw3cq1VZ3THoRJ6k.jpg", "https://youtu.be/Qi4HEs2H-k8", 3.4f, "15/01/2018");
    private static final Title incredibles = new Title("Os Incríveis", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/tE5Kdj6c2sWMxamK8WCnk2uUzCP.jpg", "https://youtu.be/eZbzbC9285I", 4.3f, "21/07/2018");
    private static final Title bighero = new Title("Operação Big Hero", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/9gLu47Zw5ertuFTZaxXOvNfy78T.jpg", "https://youtu.be/OvgyXKDXdZY", 4.2f, "13/12/2018");
    private static final Title strange = new Title("Doutor Estranho", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/4PiiNGXj1KENTmCBHeN6Mskj2Fq.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "19/09/2018");
    private static final Title jurassic = new Title("Jurassic World: Reino Ameaçado", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/nR6LhLKk5BsholuIHIAGemMKPKy.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "18/05/2018");
    private static final Title future = new Title("Future World", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/kMA0IalnEEa0PaHRUzzjpTu5xXQ.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "21/05/2018");
    private static final Title incredibles2 = new Title("Os Incríveis 2", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/pWIi2JaMO1A7TWDltwk0TifRJco.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "14/06/2018");
    private static final Title ilha = new Title("Ilha de Cachorros", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/fsS7sWTsmnQmzXCH1tG4DZdRXOQ.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "03/04/2018");
    private static final Title sexy = new Title("Sexy por Acidente", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/pl8RjlcRuo3qtK6jHemLeqorDgS.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "13/05/2015");

    private static final Title westworld = new Title("Westworld", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6aj09UTMQNyfSfk0ZX8rYOEsXL2.jpg", "https://youtu.be/JctIuZfSsa4", 4.0f, "13/05/2017");

    public static List<Title> getRecommendations() {
        List<Title> recommendations = new ArrayList<>();
        avengers.setReleaseDate("20/07/2019");
        deadpool.setReleaseDate("12/10/2018");
        westworld.setReleaseDate("04/05/2018");

        recommendations.add(avengers);
        recommendations.add(deadpool);
        recommendations.add(westworld);

        return recommendations;
    }

    public static List<Title> getTop() {
        List<Title> titles = new ArrayList<>();
        avengers.setGenres("Ação / Aventura / Comédia");
        avengers.setWatchCount(67345);
        titles.add(avengers);
        incredibles.setGenres("Ação / Aventura / Animação");
        incredibles.setWatchCount(2398777);
        titles.add(incredibles);
        westworld.setGenres("Ação / Ficção Científica / Drama");
        westworld.setWatchCount(231123);
        titles.add(westworld);
        jurassic.setGenres("Aventura / Ficção Científica");
        jurassic.setWatchCount(90883);
        titles.add(jurassic);
        strange.setGenres("Ação / Aventura");
        strange.setWatchCount(12189);
        titles.add(strange);
        incredibles2.setGenres("Ação / Aventura / Animação");
        incredibles2.setWatchCount(100988);
        titles.add(incredibles2);
        readyP1.setGenres("Ação / Aventura / Ficção Científica");
        readyP1.setWatchCount(763451);
        titles.add(readyP1);
        ilha.setGenres("Ação / Aventura / Comédia");
        ilha.setWatchCount(456233);
        titles.add(ilha);
        return titles;
    }

    public static List<Title> getAll() {
        return Arrays.asList(readyP1, jumanji, blade, kingsman, maze, incredibles, bighero, strange,
                jurassic, future, incredibles2, ilha, sexy, solo, devolucoes, gameNight, deadpool,
                gameNight, gnomeu);
    }

    public static List<TitleWatchlist> getWatchlist() {
        List<TitleWatchlist> list = new ArrayList<>();
        readyP1.setDescription("Num futuro distópico, situado em 2044, Wade Watts, como o resto da humanidade, prefere a realidade virtual do jogo OASIS ao mundo real. No jogo, seus usuários devem descobrir a chave de um quebra-cabeça diabólico, baseado na cultura do final do século XX, para conquistar um prêmio de valor inestimável. Para vencê-lo, porém, Watts terá de abandonar a existência virtual e ceder a uma vida de amor e realidade da qual sempre tentou fugir.");
        list.add(new TitleWatchlist(readyP1, getRandomDate()));
        incredibles2.setDescription("A família de super-heróis favorita de todo mundo está de volta em Os Incríveis 2 — mas dessa vez, Helena está sendo o destaque, deixando Bob em casa com Violeta e Flecha para se aventurar no dia a dia heroico de vida “normal”. É uma transição difícil para todo mundo, sendo os super poderes emergentes de Zezé o fator mais complicado. Quando um novo vilão traça uma trama brilhante e perigosa, a família e Gelado devem encontrar uma maneira de trabalhar juntos novamente — o que é mais fácil dizer do que fazer, mesmo quando são incríveis.");
        list.add(new TitleWatchlist(incredibles2, getRandomDate()));
        strange.setDescription("Da Marvel Studios vem “Doctor Strange”, a história do mundialmente famoso neurocirurgião Dr. Stephen Strange, cuja vida muda para sempre depois de um terrível acidente de carro, que o rouba do uso de suas mãos. Quando a medicina tradicional o falha, ele é forçado a procurar por cura e esperança, em um lugar improvável - um enclave misterioso conhecido como Kamar-Taj. Ele rapidamente descobre que isso não é apenas um centro de cura, mas também a linha de frente de uma batalha contra as forças ocultas invisíveis empenhadas em destruir nossa realidade. Em pouco tempo, Strange - armado com poderes mágicos recém-adquiridos - é forçado a escolher se quer retornar à sua vida de fortuna e status ou deixar tudo para trás para defender o mundo como o mais poderoso feiticeiro existente.");
        list.add(new TitleWatchlist(strange, getRandomDate()));
        bighero.setDescription("Cidade de San Fransokyo, Estados Unidos. Hiro Hamada (voz de Ryan Potter) é um garoto prodígio que, aos 13 anos, criou um poderoso robô para participar de lutas clandestinas, onde tenta ganhar um bom dinheiro. Seu irmão, Tadashi (voz de Daniel Henney), deseja atraí-lo para algo mais útil e resolve levá-lo até o laboratório onde trabalha, que está repleto de invenções. Hiro conhece os amigos de Tadashi e logo se interessa em estudar ali. Para tanto ele precisa fazer a apresentação de uma grande invenção, de forma a convencer o professor Callahan (James Cromwell) a matriculá-lo. Entretanto, as coisas não saem como ele imaginava e Hiro, deprimido, encontra auxílio inesperado através do robô inflável Baymax (voz Scott Adsit), criado pelo irmão.");
        list.add(new TitleWatchlist(bighero, getRandomDate()));
        solo.setDescription("Embarque na Millennium Falcon e viaje para uma galáxia distante em uma nova aventura com o mais amado canalha da galáxia. Através de uma série de ousadas aventuras no obscuro e perigoso submundo do crime, Han Solo encontra seu poderoso futuro copiloto Chewbacca e encontra o famoso jogador Lando Calrissian, em uma jornada que definirá o curso de um dos heróis mais improváveis da saga Star Wars.");
        list.add(new TitleWatchlist(solo, getRandomDate()));
        return list;
    }

    public static long getRandomDate() {
        int random = (int) (Math.random() * 400);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, random - 200);
        return calendar.getTimeInMillis();
    }

    public static Title getTitle() {
        Title title = new Title(
                "Homem-Aranha: De Volta ao Lar",
                "https://image.tmdb.org/t/p/original/q2BrsPEztd0L1cueuFIZakHObl7.jpg",
                "https://youtu.be/KAemcLqWLH4",
                8.9f,
                "07 de Julho de 2017");

        title.setDescription("Depois de atuar ao lado dos Vingadores, chegou a hora do pequeno Peter Parker (Tom Holland) voltar para casa e para a sua vida, já não mais tão normal. Lutando diariamente contra pequenos crimes nas redondezas, ele pensa ter encontrado a missão de sua vida quando o terrível vilão Abutre (Michael Keaton) surge amedrontando a cidade. O problema é que a tarefa não será tão fácil como ele imaginava.");
        title.setRuntime(133);
        title.setClassification("PG-13");
        title.setGenres("Ação / Aventura");
        title.setMovie(true);
        title.setImageHorizontal("https://image.tmdb.org/t/p/original/tPpYGm2mVecue7Bk3gNVoSPA5qn.jpg");
        title.setColorPalette(Color.parseColor("#42a5f5"));
        title.setHasColorPalette(true);
        title.setLikes(49473);

        return title;
    }

    public static List<Cast> getCast() {
        List<Cast> cast = new ArrayList<>();
        cast.add(new Cast(1, "https://image.tmdb.org/t/p/w138_and_h175_face/dO9FYcPPShhiYQ9D50Takd2W8xo.jpg", "Tom Holland", "Peter Parker / Spider-Man"));
        cast.add(new Cast(1, "https://image.tmdb.org/t/p/w138_and_h175_face/myVdrYNGTgqunLfUSaM8DuVD7DL.jpg", "Michael Keaton", "Adrian Toomes / The Vulture"));
        cast.add(new Cast(1, "https://image.tmdb.org/t/p/w138_and_h175_face/1YjdSym1jTG7xjHSI0yGGWEsw5i.jpg", "Robert Downey Jr.", "Tony Stark / Iron Man"));
        cast.add(new Cast(1, "https://image.tmdb.org/t/p/w138_and_h175_face/l87UKhZ1ethWmn8lksmLZ2LcQJ1.jpg", "Marisa Tomei", "May Parker"));
        cast.add(new Cast(1, "https://image.tmdb.org/t/p/w138_and_h175_face/sJSGJwGa3hjMlJNUCxF7wQwo7fb.jpg", "Jon Favreau", "Harold \"Happy\" Hogan"));
        cast.add(new Cast(1, "https://image.tmdb.org/t/p/w138_and_h175_face/6WPolY7Wd3GMiuN1dPxYZX7liik.jpg", "Zendaya", "Michelle \"MJ\" Jones"));
        cast.add(new Cast(1, "https://image.tmdb.org/t/p/w138_and_h175_face/k3rnbeFB4GEp9vOvgdZ33M2IEHq.jpg", "Laura Harrier", "Liz Toomes"));
        return cast;
    }

    public static List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("Eu achei muito legal", "João Paulo", "https://avatars1.githubusercontent.com/u/9421614?s=460&v=4", 0));
        comments.add(new Comment("Filme massa! Recomendo para todo mundo", "Nathan Almeida", "https://avatars0.githubusercontent.com/u/9421457?s=460&v=4", 0));
        comments.add(new Comment("Muito bom, o diretor e escritores estão de parabens por esta belissima obra de arte", "Marcelo Bião", "https://avatars3.githubusercontent.com/u/8891971?s=460&v=4", 0));

        return comments;
    }
}
