package com.example.demo_jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Card {
        @Id
        private Integer id;
        private String name;
        private Integer level;
        @OneToOne
        private Element element;
        private Integer top;
        @Column(name = "\"right\"")
        private Integer right;
        private Integer bottom;
        @Column(name = "\"left\"")
        private Integer left;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Integer getLevel() {
                return level;
        }

        public void setLevel(Integer level) {
                this.level = level;
        }

        public Element getElement() {
                return element;
        }

        public void setElement(Element element) {
                this.element = element;
        }

        public Integer getTop() {
                return top;
        }

        public void setTop(Integer top) {
                this.top = top;
        }

        public Integer getRight() {
                return right;
        }

        public void setRight(Integer right) {
                this.right = right;
        }

        public Integer getBottom() {
                return bottom;
        }

        public void setBottom(Integer bottom) {
                this.bottom = bottom;
        }

        public Integer getLeft() {
                return left;
        }

        public void setLeft(Integer left) {
                this.left = left;
        }
}