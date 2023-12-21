CC = javac

SRCS = \
		$(wildcard *.java)

CLASS = $(SRCS:.java=.class)


%.class: %.java
	@ $(CC) $<

all: $(CLASS)

clean:
	@ rm -f $(CLASS)

re: clean all

.PHONY: all clean re