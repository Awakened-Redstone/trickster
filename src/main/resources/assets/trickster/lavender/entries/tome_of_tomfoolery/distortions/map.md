```json
{
  "title": "Map Manipulation",
  "icon": "minecraft:filled_map",
  "category": "trickster:distortions"
}
```

This chapter describes patterns that can be used to work with maps. 
Maps allow for an association between one fragment and another, 
similarly to how a dictionary associates a word to a definition.


;;;;;

<|glyph@trickster:templates|trick-id=trickster:map_insert,title=Charting Stratagem|>

{any: any}, [any, any]... -> any

---

Inserts key-value pairs into the given map.

;;;;;

<|glyph@trickster:templates|trick-id=trickster:map_get,title=Navigator's Distortion|>

{any: any}, any -> any

---

If there is a value associated with the given fragment, returns it. Otherwise, returns void.

;;;;;

<|glyph@trickster:templates|trick-id=trickster:map_remove,title=Erasing Distortion|>

{any: any}, any -> {any: any}

---

If there is a value associated with the given fragment, removes it.