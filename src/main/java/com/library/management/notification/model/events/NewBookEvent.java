package com.library.management.notification.model.events;

import com.library.management.notification.model.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class NewBookEvent implements LibEvent, Serializable {

    private static final long serialVersionUID = 5574517713388699077L;

    private final BookDto bookDto;

    @Override
    public String getEventDetail() {
        StringBuffer sb = new StringBuffer();

        sb.append("Title: ").append(bookDto.getTitle()).append("\n")
        .append("Author: ").append(bookDto.getAuthor()).append("\n");

        return sb.toString();
    }
}
