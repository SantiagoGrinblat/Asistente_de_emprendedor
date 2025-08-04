package com.santidev.entrepreneurassistant.utils.cards

import android.Manifest.permission
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.santidev.entrepreneurassistant.CardViewModel
import com.santidev.entrepreneurassistant.database.CardEntity
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddCard(
  viewModel: CardViewModel = koinViewModel(),
  onNavigateBack: () -> Unit,
  onSaveCard: (CardEntity) -> Unit,
  cardToEdit: CardEntity? = null
  
) {
  var title by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }
  var price by remember { mutableStateOf(cardToEdit?.price?.toString() ?: "") }
  var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
  var showRationaleDialog by remember { mutableStateOf(false) }
  
  val context = LocalContext.current
  //guarda el permiso para acceder a la imagen en el futuro, incluso despues de cerrar y abrir la app.
  val imagePickerLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
  ) { uri: Uri? ->
    uri?.let {
      selectedImageUri = it
      
      // Solicita persistencia del permiso para recordar la imagen
      try {
        context.contentResolver.takePersistableUriPermission(
          it,
          Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
      } catch (e: SecurityException) {
        Log.e("AddCard", "No se pudo tomar permiso persistente", e)
      }
    }
  }
  
  //Solicita el permiso segun la version del dispositivo
  val permission = remember {
    when {
      Build.VERSION.SDK_INT > Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
        //Permiso para android 15 o mas
        listOf(
          permission.READ_MEDIA_IMAGES,
          permission.READ_MEDIA_VISUAL_USER_SELECTED
        )
      }
      
      //Permiso para android 14
      Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU -> {
        listOf(permission.READ_MEDIA_IMAGES)
      }
      
      else -> {
        //Permiso para android 13 o menos
        listOf(permission.READ_EXTERNAL_STORAGE)
      }
    }
  }
  
  val multiplePermissionState = rememberMultiplePermissionsState(permission)
  
  //Gestiona multiples permisos segun la version del dispositivo
  val hasImageAccess = remember(multiplePermissionState.allPermissionsGranted) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
      multiplePermissionState.permissions.any { it.status.isGranted }
    } else {
      multiplePermissionState.allPermissionsGranted
    }
  }
  
  //Gestiona el permiso de la app para acceder a la galeria y dar una explicacion de porque se necesita
  val handleImageSelection = {
    when {
      hasImageAccess -> {
        imagePickerLauncher.launch("image/*")
      }
      
      multiplePermissionState.shouldShowRationale -> {
        showRationaleDialog = true
        //Explicacion del porque se solicita el permiso de la app
      }
      
      else -> {
        multiplePermissionState.launchMultiplePermissionRequest()
      }
    }
  }
  
  if (showRationaleDialog) {
    AlertDialog(
      onDismissRequest = { showRationaleDialog = false },
      title = { Text("Permiso requerido") },
      text = {
        Text("La app necesita permiso para acceder a tu galería y permitirte seleccionar una imagen para agregar a tu tarjeta. \n \nNo se almacenan ni registran imágenes fuera del dispositivo, ya que toda la información se guarda localmente en la memoria interna.")
      },
      confirmButton = {
        TextButton(onClick = {
          showRationaleDialog = false
          multiplePermissionState.launchMultiplePermissionRequest()
        }) {
          Text("Conceder permiso")
        }
      },
      dismissButton = {
        TextButton(onClick = { showRationaleDialog = false }) {
          Text("Cancelar")
        }
      }
    )
  }
  
  
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .clickable { handleImageSelection() },
      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
      colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant
      )
    ) {
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
      ) {
        if (selectedImageUri != null) {
          AsyncImage(
            model = selectedImageUri,
            contentDescription = "Imagen seleccionada",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
          )
        } else {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Icon(
              imageVector = Icons.Default.Add,
              contentDescription = "Seleccionar imagen",
              modifier = Modifier.size(48.dp),
              tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = if (hasImageAccess) {
                "Toca para seleccionar imagen"
              } else {
                "Permiso requerido para seleccionar imagen"
              },
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              textAlign = TextAlign.Center
            )
          }
        }
      }
    }
    
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE && !hasImageAccess) {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
      ) {
        Column(
          modifier = Modifier.padding(16.dp)
        ) {
          Text(
            text = "Acceso a fotos",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Esta app necesita acceso a tus fotos para seleccionar imágenes. Puedes elegir dar acceso completo o seleccionar solo algunas fotos.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
          )
        }
      }
    }
    
    OutlinedTextField(
      value = title,
      onValueChange = { title = it },
      label = { Text("Título del producto") },
      modifier = Modifier.fillMaxWidth()
    )
    
    OutlinedTextField(
      value = description,
      onValueChange = { description = it },
      label = { Text("Descripción del producto") },
      modifier = Modifier.fillMaxWidth(),
      maxLines = 3
    )
    
    OutlinedTextField(
      value = price,
      onValueChange = { price = it },
      label = { Text("Cantidad del producto") },
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    
    Button(
      onClick = {
        if (title.isNotBlank() &&
          description.isNotBlank() &&
          price.isNotBlank() &&
          selectedImageUri != null
        ) {
          val card = CardEntity(
            title = title,
            description = description,
            price = price.toIntOrNull() ?: 0,
            imageUri = selectedImageUri.toString()
          )
          viewModel.insertCard(card)
          onNavigateBack()
        }
      },
      modifier = Modifier.fillMaxWidth(),
      enabled = title.isNotBlank() &&
          description.isNotBlank() &&
          price.isNotBlank() &&
          selectedImageUri != null
    ) {
      Text("Guardar")
    }
  }
}