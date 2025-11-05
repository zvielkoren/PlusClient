from PIL import Image, ImageDraw, ImageFont
import os

# List of all power items from ModItems.java
power_items = [
    "speed_power", "dogs_power", "blindness_power", "cage_power",
    "emerald_power", "immunity_power", "creeper_power", "poison_power",
    "totem_power", "vex_power", "dolphin_power", "grappling_power",
    "pearl_power", "lifesteal_power", "heart_power", "shield_piercing_power",
    "shrink_power", "cobweb_power", "mace_power", "invisibility_power",
    "strength_power", "slowness_power", "weakness_power", "wither_power",
    "kochavlet_power", "general_power", "kochavlet"
]

def create_texture(item_name):
    # Create a new 16x16 image with transparent background
    img = Image.new('RGBA', (16, 16), (0, 0, 0, 0))
    d = ImageDraw.Draw(img)
    
    # Generate a color based on the item name hash
    color = hash(item_name) % 0xFFFFFF
    r = (color >> 16) & 0xFF
    g = (color >> 8) & 0xFF
    b = color & 0xFF
    
    # Draw a colored rectangle
    d.rectangle([1, 1, 14, 14], fill=(r, g, b, 255), outline=(0, 0, 0, 255))
    
    # Add text (first letter of each word)
    text = ''.join([word[0].upper() for word in item_name.split('_')])
    
    # Try to load a small font, fall back to default
    try:
        font = ImageFont.truetype("arial.ttf", 8)
    except:
        font = ImageFont.load_default()
    
    # Calculate text position (centered)
    bbox = d.textbbox((0, 0), text, font=font)
    text_width = bbox[2] - bbox[0]
    text_height = bbox[3] - bbox[1]
    position = ((16 - text_width) // 2, (16 - text_height) // 2)
    
    # Draw text
    d.text(position, text, fill=(255, 255, 255, 255), font=font)
    
    # Save the image
    img.save(f"{item_name}.png")
    print(f"Generated texture for {item_name}")

# Ensure output directory exists
os.makedirs("generated_textures", exist_ok=True)
os.chdir("generated_textures")

# Generate all textures
for item in power_items:
    create_texture(item)

print("\nAll textures generated in the 'generated_textures' directory.")
print("Please move them to: src/main/resources/assets/plussmp/textures/item/")
