package com.aluracursos.literalura.models;

import java.util.List;

public class GutendexResponse {
    private List<Book> results;

    // Getter

    public List<Book> getResults() {
        return results;
    }

    // Subclase para representar un libro
    public static class Book {
        private String title;
        private List<Author> authors;
        private List<String> languages;
        private int download_count;

        // Getters and setters

        public String getTitle() {
            return title;
        }

        public String getAuthorFirstName() {
            if (authors != null && !authors.isEmpty()) {
                String fullName = authors.get(0).getName();
                String[] nameParts = fullName.split(" ");
                return nameParts.length > 0 ? nameParts[0] : "";
            }
            return "";
        }

        public String getAuthorLastName() {
            if (authors != null && !authors.isEmpty()) {
                String fullName = authors.get(0).getName();
                String[] nameParts = fullName.split(" ");
                return nameParts.length > 1 ? nameParts[nameParts.length - 1] : "";
            }
            return "";
        }

        public Integer getAuthorBirthYear() {
            if (authors != null && !authors.isEmpty()) {
                Integer birthYear = authors.get(0).getBirth_year();
                return birthYear;
            }
            return null; // Retorna null si no hay información disponible
        }

        public Integer getAuthorDeathYear() {
            if (authors != null && !authors.isEmpty()) {
                Integer deathYear = authors.get(0).getDeath_year();
                return deathYear;
            }
            return null;
        }

        public String getLanguage() {
            if (languages != null && !languages.isEmpty()) {
                return languages.get(0);
            }
            return "";
        }

        public int getDownloads() {
            return download_count;
        }

        public static class Author {
            private String name;
            private Integer birth_year;
            private Integer death_year;

            // Getters and setters
            public String getName() {
                return name;
            }

            public Integer getBirth_year() {
                return birth_year;
            }

            public Integer getDeath_year() {
                return death_year;
            }
        }
    }
}

