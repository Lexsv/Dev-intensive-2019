package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage(
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var text: String?
): BaseMessage("$lastId", from, chat, isIncoming, date) {
    override fun formatMessage(): String = "id:$id ${from?.firstName} ${if (isIncoming) "получил сообщение " 
                                                                            else "отправил сообщение "}" +
                                            "сообщение \"$text\" ${date.humanizeDiff()}"
}
