# Pillow

A Minecraft 1.21.1 mod that adds the **Pillow** — a joke melee weapon for pillow fights.

Available for **NeoForge** and **Fabric**.

## Behaviour

The Pillow deals no damage. Instead, hitting a living entity with it sends them flying with a strong knockback, heals them 1 heart, and bursts into feathers.

## Crafting

Combine 3 Carpet (any colour) and 6 Feathers in a shaped recipe (carpet on top, feathers filling the rest). The recipe is automatically unlocked in the recipe book when you pick up a Feather.

## Building

```bash
# NeoForge jar
./gradlew :neoforge:build
# Output: neoforge/build/libs/

# Fabric jar
./gradlew :fabric:build
# Output: fabric/build/libs/
```

## Development

```bash
# Launch NeoForge dev client
./gradlew :neoforge:runClient

# Run unit tests
./gradlew :common:test
```

## Credits

Ported from the original Pillow Bedrock addon, made by edward3h and his son.

## Licence

Apache 2.0 — see [LICENSE](LICENSE).
