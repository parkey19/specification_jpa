package com.attacomsian.jpa;

import com.attacomsian.jpa.domains.Item;
import com.attacomsian.jpa.domains.Movie;
import com.attacomsian.jpa.repositories.ItemRepository;
import com.attacomsian.jpa.repositories.MovieRepository;
import com.attacomsian.jpa.repositories.specs.ItemSpecification;
import com.attacomsian.jpa.repositories.specs.MovieSpecification;
import com.attacomsian.jpa.repositories.specs.SearchCriteria;
import com.attacomsian.jpa.repositories.specs.SearchOperation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner specificationsDemo(MovieRepository movieRepository, ItemRepository itemRepository) {
        return args -> {

            // create new movies
            movieRepository.saveAll(Arrays.asList(
                    new Movie("Troy", "Drama", 7.2, 196, 2004),
                    new Movie("The Godfather", "Crime", 9.2, 178, 1972),
                    new Movie("Invictus", "Sport", 7.3, 135, 2009),
                    new Movie("Black Panther", "Action", 7.3, 135, 2018),
                    new Movie("Joker", "Drama", 8.9, 122, 2018),
                    new Movie("Iron Man", "Action", 8.9, 126, 2008)
            ));

            itemRepository.saveAll(Arrays.asList(
               new Item(1L, "RED", "A", "18"),
               new Item(2L, "BLUE", "B", "블루"),
               new Item(3L, "YELLOW", "C", "노랑"),
               new Item(4L, "GREEN", "D", "초록")
            ));

            // search movies by `genre`
//            MovieSpecification msGenre = new MovieSpecification();
//            msGenre.add(new SearchCriteria("genre", "Action", SearchOperation.EQUAL));
//            List<Movie> msGenreList = movieRepository.findAll(msGenre);
//            msGenreList.forEach(System.out::println);
//
//            // search movies by `title` and `rating` > 7
//            MovieSpecification msTitleRating = new MovieSpecification();
//            msTitleRating.add(new SearchCriteria("title", "black", SearchOperation.MATCH));
//            msTitleRating.add(new SearchCriteria("rating", 7, SearchOperation.GREATER_THAN));
//            List<Movie> msTitleRatingList = movieRepository.findAll(msTitleRating);
//            msTitleRatingList.forEach(System.out::println);
//
//            // search movies by release year < 2010 and rating > 8
//            MovieSpecification msYearRating = new MovieSpecification();
//            msYearRating.add(new SearchCriteria("releaseYear", 2010, SearchOperation.LESS_THAN));
//            msYearRating.add(new SearchCriteria("rating", 8, SearchOperation.GREATER_THAN));
//            List<Movie> msYearRatingList = movieRepository.findAll(msYearRating);
//            msYearRatingList.forEach(System.out::println);
//
//            // search movies by watch time >= 150 and sort by `title`
//            MovieSpecification msWatchTime = new MovieSpecification();
//            msWatchTime.add(new SearchCriteria("watchTime", 150, SearchOperation.GREATER_THAN_EQUAL));
//            List<Movie> msWatchTimeList = movieRepository.findAll(msWatchTime, Sort.by("title"));
//            msWatchTimeList.forEach(System.out::println);
//
//            // search movies by title <> 'white' and paginate results
//            MovieSpecification msTitle = new MovieSpecification();
//            msTitle.add(new SearchCriteria("title", "white", SearchOperation.NOT_EQUAL));
//
//            Pageable pageable = PageRequest.of(0, 3, Sort.by("releaseYear").descending());
//            Page<Movie> msTitleList = movieRepository.findAll(msTitle, pageable);

//            msTitleList.forEach(System.out::println);

            //"Crime" or  > 9.0 and  178,
//            MovieSpecification msTitle = new MovieSpecification();
//            msTitle.add(new SearchCriteria("watchTime", 178, SearchOperation.EQUAL));


//            Pageable pageable = PageRequest.of(0, 3, Sort.by("releaseYear").descending());
//            MovieSpecification msTitle = new MovieSpecification();
//            msTitle.add(new SearchCriteria("title", "The Godfather", SearchOperation.EQUAL));
//            msTitle.add(new SearchCriteria("genre", "Crime", SearchOperation.EQUAL));
//
//
//            List<Movie> movies = movieRepository.findAll(Specification.where(msTitle));
//            movies.forEach(System.out::println);

            Specification<Item> redOrBlue = ItemSpecification.withColor("RED", "BLUE");
            Specification<Item> aOrb = ItemSpecification.withGradeAndName("A", "18");
//            Specification<Item> withName = ItemSpecification.withName("18");
            List<Item> all = itemRepository.findAll(redOrBlue.and(aOrb));
            all.forEach(System.out::println);

            /**
             * Hibernate:
             *     select
             *         item0_.id as id1_0_,
             *         item0_.color as color2_0_,
             *         item0_.grade as grade3_0_,
             *         item0_.name as name4_0_
             *     from
             *         item item0_
             *     where
             *         item0_.name=?
             *         and (
             *             item0_.grade=?
             *             or item0_.grade=?
             *         )
             *         and (
             *             item0_.color=?
             *             or item0_.color=?
             *         )
             * Item(id=1, color=RED, grade=A, name=18)
             */

        };
    }
}
