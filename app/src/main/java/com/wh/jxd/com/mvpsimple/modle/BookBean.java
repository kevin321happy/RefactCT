package com.wh.jxd.com.mvpsimple.modle;

import java.util.List;

/**
 * Created by kevin321vip on 2017/8/28.
 */

public class BookBean {
    /**
     * count : 1
     * start : 0
     * total : 542
     * books : [{"rating":{"max":10,"numRaters":4169,"average":"8.6","min":0},"subtitle":"张竹坡批评第一奇书","author":["兰陵笑笑生"],"pubdate":"1991","tags":[{"count":1988,"name":"金瓶梅","title":"金瓶梅"},{"count":1160,"name":"古典文学","title":"古典文学"},{"count":835,"name":"兰陵笑笑生","title":"兰陵笑笑生"},{"count":741,"name":"小说","title":"小说"},{"count":626,"name":"中国古典文学","title":"中国古典文学"},{"count":412,"name":"中国文学","title":"中国文学"},{"count":357,"name":"古典","title":"古典"},{"count":347,"name":"中国","title":"中国"}],"origin_title":"（明）兰陵笑笑生","image":"https://img1.doubanio.com/mpic/s10069398.jpg","binding":"","translator":[],"catalog":"\n      ","pages":"","images":{"small":"https://img1.doubanio.com/spic/s10069398.jpg","large":"https://img1.doubanio.com/lpic/s10069398.jpg","medium":"https://img1.doubanio.com/mpic/s10069398.jpg"},"alt":"https://book.douban.com/subject/1456692/","id":"1456692","publisher":"齐鲁出版社","isbn10":"7533300815","isbn13":"9787533300814","title":"金瓶梅","url":"https://api.douban.com/v2/book/1456692","alt_title":"（明）兰陵笑笑生","author_intro":"","summary":"本书由王汝梅与李昭恂、于凤树校点。","series":{"id":"4279","title":"明代四大奇书"},"price":"268.00元"}]
     */

    private int count;
    private int start;
    private int total;
    private List<BooksEntity> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BooksEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BooksEntity> books) {
        this.books = books;
    }

    public static class BooksEntity {
        /**
         * rating : {"max":10,"numRaters":4169,"average":"8.6","min":0}
         * subtitle : 张竹坡批评第一奇书
         * author : ["兰陵笑笑生"]
         * pubdate : 1991
         * tags : [{"count":1988,"name":"金瓶梅","title":"金瓶梅"},{"count":1160,"name":"古典文学","title":"古典文学"},{"count":835,"name":"兰陵笑笑生","title":"兰陵笑笑生"},{"count":741,"name":"小说","title":"小说"},{"count":626,"name":"中国古典文学","title":"中国古典文学"},{"count":412,"name":"中国文学","title":"中国文学"},{"count":357,"name":"古典","title":"古典"},{"count":347,"name":"中国","title":"中国"}]
         * origin_title : （明）兰陵笑笑生
         * image : https://img1.doubanio.com/mpic/s10069398.jpg
         * binding :
         * translator : []
         * catalog :

         * pages :
         * images : {"small":"https://img1.doubanio.com/spic/s10069398.jpg","large":"https://img1.doubanio.com/lpic/s10069398.jpg","medium":"https://img1.doubanio.com/mpic/s10069398.jpg"}
         * alt : https://book.douban.com/subject/1456692/
         * id : 1456692
         * publisher : 齐鲁出版社
         * isbn10 : 7533300815
         * isbn13 : 9787533300814
         * title : 金瓶梅
         * url : https://api.douban.com/v2/book/1456692
         * alt_title : （明）兰陵笑笑生
         * author_intro :
         * summary : 本书由王汝梅与李昭恂、于凤树校点。
         * series : {"id":"4279","title":"明代四大奇书"}
         * price : 268.00元
         */

        private RatingEntity rating;
        private String subtitle;
        private String pubdate;
        private String origin_title;
        private String image;
        private String binding;
        private String catalog;
        private String pages;
        private ImagesEntity images;
        private String alt;
        private String id;
        private String publisher;
        private String isbn10;
        private String isbn13;
        private String title;
        private String url;
        private String alt_title;
        private String author_intro;
        private String summary;
        private SeriesEntity series;
        private String price;
        private List<String> author;
        private List<TagsEntity> tags;
        private List<?> translator;

        public RatingEntity getRating() {
            return rating;
        }

        public void setRating(RatingEntity rating) {
            this.rating = rating;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getPubdate() {
            return pubdate;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }

        public String getOrigin_title() {
            return origin_title;
        }

        public void setOrigin_title(String origin_title) {
            this.origin_title = origin_title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getBinding() {
            return binding;
        }

        public void setBinding(String binding) {
            this.binding = binding;
        }

        public String getCatalog() {
            return catalog;
        }

        public void setCatalog(String catalog) {
            this.catalog = catalog;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public ImagesEntity getImages() {
            return images;
        }

        public void setImages(ImagesEntity images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getIsbn10() {
            return isbn10;
        }

        public void setIsbn10(String isbn10) {
            this.isbn10 = isbn10;
        }

        public String getIsbn13() {
            return isbn13;
        }

        public void setIsbn13(String isbn13) {
            this.isbn13 = isbn13;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlt_title() {
            return alt_title;
        }

        public void setAlt_title(String alt_title) {
            this.alt_title = alt_title;
        }

        public String getAuthor_intro() {
            return author_intro;
        }

        public void setAuthor_intro(String author_intro) {
            this.author_intro = author_intro;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public SeriesEntity getSeries() {
            return series;
        }

        public void setSeries(SeriesEntity series) {
            this.series = series;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<String> getAuthor() {
            return author;
        }

        public void setAuthor(List<String> author) {
            this.author = author;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public void setTags(List<TagsEntity> tags) {
            this.tags = tags;
        }

        public List<?> getTranslator() {
            return translator;
        }

        public void setTranslator(List<?> translator) {
            this.translator = translator;
        }

        public static class RatingEntity {
            /**
             * max : 10
             * numRaters : 4169
             * average : 8.6
             * min : 0
             */

            private int max;
            private int numRaters;
            private String average;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getNumRaters() {
                return numRaters;
            }

            public void setNumRaters(int numRaters) {
                this.numRaters = numRaters;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class ImagesEntity {
            /**
             * small : https://img1.doubanio.com/spic/s10069398.jpg
             * large : https://img1.doubanio.com/lpic/s10069398.jpg
             * medium : https://img1.doubanio.com/mpic/s10069398.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class SeriesEntity {
            /**
             * id : 4279
             * title : 明代四大奇书
             */

            private String id;
            private String title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class TagsEntity {
            /**
             * count : 1988
             * name : 金瓶梅
             * title : 金瓶梅
             */

            private int count;
            private String name;
            private String title;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", books=" + books +
                '}';
    }
}
