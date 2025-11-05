# List of all item IDs that need model files
$items = @(
    "speed_power",
    "dogs_power",
    "blindness_power",
    "cage_power",
    "emerald_power",
    "immunity_power",
    "creeper_power",
    "poison_power",
    "totem_power",
    "vex_power",
    "dolphin_power",
    "grappling_power",
    "pearl_power",
    "lifesteal_power",
    "heart_power",
    "shield_piercing_power",
    "shrink_power",
    "cobweb_power",
    "mace_power",
    "invisibility_power",
    "strength_power",
    "slowness_power",
    "weakness_power",
    "wither_power",
    "kochavlet_power",
    "general_power",
    "general"
)

$modelsDir = "src\main\resources\assets\plussmp\models\item"
$texturesDir = "src\main\resources\assets\plussmp\textures\item"

# Create textures directory if it doesn't exist
if (-not (Test-Path $texturesDir)) {
    New-Item -ItemType Directory -Path $texturesDir -Force | Out-Null
}

foreach ($item in $items) {
    $modelPath = Join-Path $modelsDir "$item.json"
    $texturePath = Join-Path $texturesDir "$item.png"
    
    # If texture doesn't exist, create a note about it
    if (-not (Test-Path $texturePath)) {
        Write-Host "Missing texture for: $item"
        # Update model to use missing texture indicator
        $jsonContent = @"
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "plussmp:item/missing_texture"
  },
  "display": {
    "gui": {
      "rotation": [ 0, 0, 0 ],
      "translation": [ 0, 0, 0 ],
      "scale": [ 1, 1, 1 ]
    }
  }
}
"@
        $jsonContent | Out-File -FilePath $modelPath -Encoding utf8 -Force
        Write-Host "  -> Updated model to use missing texture fallback"
    }
}

Write-Host "\nAll models have been updated!"
