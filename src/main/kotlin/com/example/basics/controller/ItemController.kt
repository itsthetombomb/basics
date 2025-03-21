package com.example.basics.controller
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/items")
class ItemController {

    @PostMapping()
    fun createItem(item: Item) {
        val x = Item()
    }

    @GetMapping("/items/{id}")
    fun getItem(@PathVariable id: String) : ResponseEntity<Item> {
        val x = id
        return ResponseEntity.ok(Item())
    }
}



data class Item(
    private val id: String = "",
    private val type: ItemType = ItemType.ITEM_TYPE_1
)

enum class ItemType {
    ITEM_TYPE_1,
    ITEM_TYPE_2
}