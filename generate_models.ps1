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
    "kochavlet",
    "general"
)

# Create models directory if it doesn't exist
$modelsDir = "src\main\resources\assets\plussmp\models\item"
if (-not (Test-Path $modelsDir)) {
    New-Item -ItemType Directory -Path $modelsDir -Force | Out-Null
}

# Create a model file for each item
foreach ($item in $items) {
    $filePath = Join-Path $modelsDir "$item.json"
    if (-not (Test-Path $filePath)) {
        $jsonContent = @"
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "plussmp:item/$item"
  }
}
"@
        $jsonContent | Out-File -FilePath $filePath -Encoding utf8
        Write-Host "Created model for: $item"
    } else {
        Write-Host "Skipped (exists): $item"
    }
}

Write-Host "\nAll model files have been generated!"
